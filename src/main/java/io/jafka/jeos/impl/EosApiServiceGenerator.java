package io.jafka.jeos.impl;

import java.io.IOException;
import java.lang.annotation.Annotation;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;

import io.jafka.jeos.exception.EosApiError;
import io.jafka.jeos.exception.EosApiErrorCode;
import io.jafka.jeos.exception.EosApiException;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class EosApiServiceGenerator {

    private static OkHttpClient httpClient;

    private static Retrofit retrofit;
    private static ObjectMapper mapper = new ObjectMapper();
    static {
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.setPropertyNamingStrategy(new SnakeCaseStrategy());
        mapper.findAndRegisterModules();
        //
        httpClient = new OkHttpClient.Builder().addInterceptor(new LoggingInterceptor()).build();

    }

    public static ObjectMapper getMapper() {
        return mapper;
    }

    public static <S> S createService(Class<S> serviceClass, String baseUrl) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(baseUrl);
        builder.client(httpClient);
        builder.addConverterFactory(JacksonConverterFactory.create(mapper));
        retrofit = builder.build();

        return retrofit.create(serviceClass);
    }

    /**
     * Execute a REST call and block until the response is received.
     */
    public static <T> T executeSync(Call<T> call) {
        try {
            Response<T> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                EosApiError apiError = getEosApiError(response);
                throw new EosApiException(apiError.getDetailedMessage(), EosApiErrorCode.get(apiError.getEosErrorCode()), apiError.getError().getDetails());
            }
        } catch (IOException e) {
            throw new EosApiException(e);
        }
    }

    /**
     * Extracts and converts the response error body into an object.
     */
    private static EosApiError getEosApiError(Response<?> response) throws IOException, EosApiException {
        return (EosApiError) retrofit.responseBodyConverter(EosApiError.class, new Annotation[0]).convert(response.errorBody());
    }
}
