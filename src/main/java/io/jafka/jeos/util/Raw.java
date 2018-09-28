package io.jafka.jeos.util;

import static java.lang.String.format;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.jafka.jeos.util.ecc.Hex;

/**
 * 
 * @author adyliu (imxylz@gmail.com)
 * @since 2018年9月11日
 */
public class Raw {
    final ByteArrayBuffer s;
    public Raw() {
        this.s = new ByteArrayBuffer(32);
    }
    public Raw(ByteArrayBuffer s) {
        this.s = s;
    }
    
    public Raw packVarint32(long v) {
        while(v >= 0x80) {
            byte b = (byte)((v & 0x7f) | 0x80);
            s.append(b);
            v >>>= 7;
        }
        s.append((byte)v);
        return this;
    }
    /**
     * uint32
     */
    public Raw pack(int v) {
       byte[] b = ByteBuffer.allocate(Integer.BYTES).order(ByteOrder.LITTLE_ENDIAN).putInt(v).array();
       s.append(b);
       return this;
    }
    public Raw packUint64(long v) {
        byte[] b = ByteBuffer.allocate(Long.BYTES).order(ByteOrder.LITTLE_ENDIAN).putLong(v).array();
        s.append(b);
        return this;
    }
    public Raw packUint32(long v) {
        byte[] b = ByteBuffer.allocate(Integer.BYTES).order(ByteOrder.LITTLE_ENDIAN).putInt((int)v).array();
        s.append(b);
        return this;
    }
    public Raw packUint16(int v) {
        s.append((byte) (v & 0x00FF) );
        s.append((byte) ((v & 0xFF00) >>> 8));
        return this;
    }
    public Raw packUint8(int v) {
        s.append((byte) (v & 0x00FF) );
        return this;
    }
    public Raw pack(String str) {
        byte[] dat = str.getBytes(StandardCharsets.UTF_8);
        packVarint32(dat.length);
        s.append(dat);
        return this;
    }
    public Raw pack(Collection<String> strs) {
        packVarint32(strs.size());
        strs.stream().forEach(this::pack);
        return this;
    }
    public Raw packPublicKey(String key) {
        if(key.startsWith("EOS")) {
            key = key.substring(3);
        }
        byte[] b = Base58.decode(key);
        //FIXME: what's this?
        s.append((byte)0);
        s.append(b, 0, b.length-4);

        return this;
    }
    public Raw pack(byte[] bytes) {
        s.append(bytes);
        return this;
    }
    //
    public byte[] bytes() {
        return s.toByteArray();
    }
    public String toHex() {
        return Hex.toHex(bytes());
    }
    //static String charmap = ".12345abcdefghijklmnopqrstuvwxyz";
    public static int charidx(char c) {
        if( c >= 'a' && c <= 'z' )
            return (c - 'a') + 6;
         if( c >= '1' && c <= '5' )
            return (c - '1') + 1;
         return 0;
    }
    /**
     * account_name name
     */
    public Raw packName(String n) {
        StringBuilder bits = new StringBuilder(64);
        for(int i=0;i<=12;i++) {
            int c = i<n.length() ? charidx(n.charAt(i)) : 0;
            int bitlen = i < 12 ? 5 : 4;
            String _b = Integer.toBinaryString(c);
            for(int j=0;j<bitlen - _b.length();j++) {
                bits.append('0');
            }
            bits.append(_b);
        }
        BigInteger vname = new BigInteger(bits.toString(), 2);
        byte[] b = ByteBuffer.allocate(Long.BYTES).order(ByteOrder.LITTLE_ENDIAN).putLong(vname.longValue()).array();
        s.append(b);
        return this;
    }
    public Raw packAsset(String v) {
        // 1.0 EOS
        Pattern p = Pattern.compile("(\\d+\\.?(\\d*)) (.+)");
        Matcher m = p.matcher(v);
        if(!m.matches()) {
            throw new RuntimeException("ERROR assert format");
        }
        // 1.000 EOS
        // group1 1.000
        // group2 000
        // group3 EOS
        long amount = Long.parseLong(m.group(1).replaceAll("\\.", ""));
        int precision = m.group(2).length();//3
        String symbol = m.group(3);//EOS
        //
        s.append(amount);
        s.append((byte)precision);
        s.append(symbol.getBytes());
        for(int i=0;i<7-symbol.length();i++) {
            s.append(0x0);
        }
        return this;
    }
    @Override
    public String toString() {
        return this.toHex();
    }
    public static byte[] concat(byte[] a, byte[] b) {
        byte[] c = new byte[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }
    public static byte[] copy(byte[] src, int start, int length) {
        byte[] c = new byte[length];
        System.arraycopy(src, start, c, 0, length);
        return c;
    }
    
}
