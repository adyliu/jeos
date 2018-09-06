package io.jafka.jeos.util;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class SHA {

    public static byte[] sha256(final String text) {
        Objects.requireNonNull(text);
        return sha256(text.getBytes(StandardCharsets.UTF_8));
    }

    public static byte[] sha256(final byte[] b) {
        Objects.requireNonNull(b);
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(b);
            return messageDigest.digest();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static byte[] hmacSha256(byte[] data, byte[] key) {
        try {
            SecretKeySpec signingKey = new SecretKeySpec(key, "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);
            return mac.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
