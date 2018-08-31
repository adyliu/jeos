package io.jafka.jeos;

import io.jafka.jeos.impl.EosApiRestClientImpl;

public abstract class EosApiFactory {

    public static EosApi create(String baseUrl) {
        return new EosApiRestClientImpl(baseUrl);
    }

    public static EosApi create(String walletBaseUrl, String chainBaseUrl, String historyBaseUrl) {
        return new EosApiRestClientImpl(walletBaseUrl, chainBaseUrl, historyBaseUrl);
    }

}
