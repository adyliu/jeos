package io.jafka.jeos;

import io.jafka.jeos.impl.EosApiRestClientImpl;
import io.jafka.jeos.impl.LocalApiImpl;

public abstract class EosApiFactory {

    public static EosApi create(String baseUrl) {
        return new EosApiRestClientImpl(baseUrl);
    }

    public static EosApi create(String baseUrl, String bearerToken) {
        return new EosApiRestClientImpl(baseUrl, bearerToken);
    }

    public static EosApi create(String walletBaseUrl, String chainBaseUrl, String historyBaseUrl) {
        return new EosApiRestClientImpl(walletBaseUrl, chainBaseUrl, historyBaseUrl);
    }

    public static EosApi create(String walletBaseUrl, String chainBaseUrl, String historyBaseUrl, String bearerToken) {
        return new EosApiRestClientImpl(walletBaseUrl, chainBaseUrl, historyBaseUrl, bearerToken);
    }
    
    public static LocalApi createLocalApi() {
        return new LocalApiImpl();
    }
}
