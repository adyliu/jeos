package io.jafka.jeos.impl;

import java.util.UUID;

import io.jafka.jeos.LocalApi;
import io.jafka.jeos.util.KeyUtil;

/**
 * 
 * @author adyliu (imxylz@gmail.com)
 * @since 2018年9月6日
 */
public class LocalApiImpl implements LocalApi {

    @Override
    public String createPrivateKey() {
        return KeyUtil.createPrivateKey(UUID.randomUUID().toString());
    }

    @Override
    public String toPublicKey(String privateKey) {
        return KeyUtil.toPublicKey(privateKey);
    }
}
