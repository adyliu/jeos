package io.jafka.jeos.util;

import java.math.BigInteger;
import java.util.Objects;
import java.util.UUID;

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
    
    public static String createPrivateKey(String seed) {
        Objects.requireNonNull(seed);
        byte[] a = { (byte) 0x80 };
        byte[] b = new BigInteger(SHA.sha256(seed)).toByteArray();
        byte[] private_key = ByteUtils.concat(a, b);
        byte[] checksum = SHA.sha256(private_key);
        checksum = SHA.sha256(checksum);
        byte[] check = ByteUtils.copy(checksum, 0, 4);
        byte[] pk = ByteUtils.concat(private_key, check);
        return Base58.encode(pk);
    }
    private static BigInteger privateKey(String pk) {
        byte[] private_wif = Base58.decode(pk);
        byte version = (byte) 0x80;
        if (private_wif[0] != version) {
            throw new IllegalArgumentException( "Expected version " + 0x80 + ", instead got " + version);
        }
        byte[] private_key = ByteUtils.copy(private_wif, 0, private_wif.length - 4);
        byte[] new_checksum = SHA.sha256(private_key);
        new_checksum = SHA.sha256(new_checksum);
        new_checksum = ByteUtils.copy(new_checksum, 0, 4);
        byte[] last_private_key = ByteUtils.copy(private_key, 1, private_key.length - 1);
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
        csum = ByteUtils.copy(csum, 0, 4);
        byte[] addy = ByteUtils.concat(pub_buf, csum);
        StringBuffer bf = new StringBuffer(address_prefix);
        bf.append(Base58.encode(addy));
        return bf.toString();
    }
    
    public static void main(String[] args) throws Exception {
        System.out.println(createPrivateKey(UUID.randomUUID().toString()));
        //5JHEuLb2UX9hsj17XZ2ZvJDhYJEWZJFhGXpKjsdiKDrnhcpeYGn -> EOS51XY9wXXs9JTDUm2TojHHUPu26D2UGzTY64qh9AeH8hhwxYctt
        System.out.println(toPublicKey("5JHEuLb2UX9hsj17XZ2ZvJDhYJEWZJFhGXpKjsdiKDrnhcpeYGn"));
    }
}
