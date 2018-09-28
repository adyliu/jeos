package io.jafka.jeos.util;

import java.math.BigInteger;
import java.util.Objects;
import java.util.UUID;

import io.jafka.jeos.util.ecc.Ecdsa;
import io.jafka.jeos.util.ecc.Hex;
import io.jafka.jeos.util.ecc.Point;
import io.jafka.jeos.util.ecc.Ripemd160;
import io.jafka.jeos.util.ecc.Secp256k;


/**
 * 
 * @author adyliu (imxylz@gmail.com)
 * @since 2018年9月5日
 */
public class KeyUtil {
    public static final String address_prefix = "EOS";

    public static final Secp256k secp = new Secp256k();
    public static String createPrivateKey() {
        return createPrivateKey(UUID.randomUUID().toString());
    }
    public static String createPrivateKey(String seed) {
        Objects.requireNonNull(seed);
        byte[] a = { (byte) 0x80 };
        byte[] b = new BigInteger(SHA.sha256(seed)).toByteArray();
        byte[] private_key = Raw.concat(a, b);
        byte[] checksum = SHA.sha256(private_key);
        checksum = SHA.sha256(checksum);
        byte[] check = Raw.copy(checksum, 0, 4);
        byte[] pk = Raw.concat(private_key, check);
        return Base58.encode(pk);
    }
    private static BigInteger privateKey(String pk) {
        byte[] private_wif = Base58.decode(pk);
        byte version = (byte) 0x80;
        if (private_wif[0] != version) {
            throw new IllegalArgumentException( "Expected version " + 0x80 + ", instead got " + version);
        }
        byte[] private_key = Raw.copy(private_wif, 0, private_wif.length - 4);
        byte[] new_checksum = SHA.sha256(private_key);
        new_checksum = SHA.sha256(new_checksum);
        new_checksum = Raw.copy(new_checksum, 0, 4);
        byte[] last_private_key = Raw.copy(private_key, 1, private_key.length - 1);
        BigInteger d = new BigInteger(Hex.toHex(last_private_key), 16);
        return d;
    }
    public static String toPublicKey(String privateKey) {
        Objects.requireNonNull(privateKey);
        // private key
        BigInteger d = privateKey(privateKey);
        // publick key
        Point ep = secp.G().multiply(d);
        byte[] pub_buf = ep.getEncoded();
        byte[] csum = Ripemd160.from(pub_buf).bytes();
        csum = Raw.copy(csum, 0, 4);
        byte[] addy = Raw.concat(pub_buf, csum);
        StringBuffer bf = new StringBuffer(address_prefix);
        bf.append(Base58.encode(addy));
        return bf.toString();
    }
    
    public static String signHash(String pk, byte[] b) {
        String dataSha256 = Hex.toHex(SHA.sha256(b));
        BigInteger e = new BigInteger(dataSha256, 16);
        int nonce = 0;
        int i = 0;
        BigInteger d = privateKey(pk);
        Point Q = secp.G().multiply(d);
        nonce = 0;
        Ecdsa ecd = new Ecdsa(secp);
        Ecdsa.SignBigInt sign;
        while (true) {
            sign = ecd.sign(dataSha256, d, nonce++);
            byte der[] = sign.getDer();
            byte lenR = der[3];
            byte lenS = der[5 + lenR];
            if (lenR == 32 && lenS == 32) {
                i = ecd.calcPubKeyRecoveryParam(e, sign, Q);
                i += 4; // compressed
                i += 27; // compact // 24 or 27 :( forcing odd-y 2nd key candidate)
                break;
            }
        }
        byte[] pub_buf = new byte[65];
        pub_buf[0] = (byte) i;
        System.arraycopy(sign.getR().toByteArray(), 0, pub_buf, 1, sign.getR().toByteArray().length);
        System.arraycopy(sign.getS().toByteArray(), 0, pub_buf, sign.getR().toByteArray().length + 1,
                sign.getS().toByteArray().length);

        byte[] checksum = Ripemd160.from(Raw.concat(pub_buf, "K1".getBytes())).bytes();

        byte[] signatureString = Raw.concat(pub_buf, Raw.copy(checksum, 0, 4));

        return "SIG_K1_" + Base58.encode(signatureString);
    }
    
}
