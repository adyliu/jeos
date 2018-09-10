package io.jafka.jeos;

import io.jafka.jeos.core.common.SignArg;

/**
 * Local API with EOS without RPC
 * @author adyliu (imxylz@gmail.com)
 * @since 2018年9月6日
 */
public interface LocalApi {

    String createPrivateKey();
    
    String toPublicKey(String privateKey);
}
