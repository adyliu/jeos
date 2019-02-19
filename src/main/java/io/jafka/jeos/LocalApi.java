package io.jafka.jeos;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jafka.jeos.core.common.SignArg;
import io.jafka.jeos.core.request.chain.transaction.PushTransactionRequest;
import io.jafka.jeos.impl.EosApiServiceGenerator;

/**
 * Local API with EOS without RPC
 * 
 * @author adyliu (imxylz@gmail.com)
 * @since 2018年9月6日
 */
public interface LocalApi {

    String createPrivateKey(byte[] seed);

    String toPublicKey(String privateKey);

    PushTransactionRequest transfer(SignArg arg,String privateKey, String from, String to, String quantity, String memo);
    
    default ObjectMapper getObjectMapper() {
        return EosApiServiceGenerator.getMapper();
    }
}
