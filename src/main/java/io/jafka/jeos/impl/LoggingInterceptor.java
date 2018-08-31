package io.jafka.jeos.impl;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

@Slf4j
public class LoggingInterceptor implements Interceptor {
    static AtomicLong _seq = new AtomicLong(0);

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        final long index = _seq.incrementAndGet();
        long t1 = System.currentTimeMillis();
        log.info("OkHttp-{}-1-->head {} {}", index, request.url(), request.headers());

        if (request.body() != null) {
            Buffer requestBuffer = new Buffer();
            request.body().writeTo(requestBuffer);
            log.info("OkHttp-{}-2-->body {}", index, requestBuffer.readUtf8());
        }

        Response response = chain.proceed(request);

        long t2 = System.currentTimeMillis();
        log.info("OkHttp-{}-3--<head {} {}ms {}", index, request.url(), (t2 - t1), response.headers());

        MediaType contentType = response.body().contentType();
        String content = response.body().string();
        log.info("OkHttp-{}-4--<body {}", index, content);

        ResponseBody wrappedBody = ResponseBody.create(contentType, content);
        return response.newBuilder().body(wrappedBody).build();
    }
}