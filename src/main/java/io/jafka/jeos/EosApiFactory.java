package io.jafka.jeos;

import io.jafka.jeos.impl.EosApiRestClientImpl;
import io.jafka.jeos.impl.LocalApiImpl;

public abstract class EosApiFactory {
    /**
     * create api from endpoint
     * <p>server list: https://www.eosdocs.io/resources/apiendpoints/</p>
     * @param baseUrl the endpoint address
     * @return api client
     * @see https://www.eosdocs.io/resources/apiendpoints/
     */
    public static EosApi create(String baseUrl) {
        return new EosApiRestClientImpl(baseUrl);
    }

    public static EosApi create(String walletBaseUrl, String chainBaseUrl, String historyBaseUrl) {
        return new EosApiRestClientImpl(walletBaseUrl, chainBaseUrl, historyBaseUrl);
    }
    
    public static LocalApi createLocalApi() {
        return new LocalApiImpl();
    }
}
