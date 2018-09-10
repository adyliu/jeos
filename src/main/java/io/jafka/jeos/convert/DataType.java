package io.jafka.jeos.convert;

import static java.lang.String.format;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.jafka.jeos.util.ByteUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Getter
public enum DataType {

    name("name"), //
    asset("asset"), //
    string("string"), //
    key("key"), //
    unit16("unit16"), //
    unit32("unit32"), //
    varint32("varint32"), //
    unit64("unit64");

    private String code;

    public byte[] encode(String v, TokenFunction f) {
        switch (this) {
        case name:
            return ByteUtils.writeName(v);
        case asset:
            if(f == TokenFunction.transfer || f == TokenFunction.delegate) {
                Pattern p = Pattern.compile("(\\d+\\.?(\\d*)) (.*)");
                Matcher m = p.matcher(v);
                if(!m.matches()) {
                    throw new RuntimeException("ERROR assert format");
                }
                v = format("%s %d,%s@eosio.token", m.group(1), m.group(2).length(),m.group(3));
                System.out.println(v);
            }
            return ByteUtils.writerAsset(v);
        case unit32:
            return ByteUtils.writerUnit32(v);
        case unit16:
            return ByteUtils.writerUnit16(v);
        case key:
            return ByteUtils.writerKey(v);
        case varint32:
            return ByteUtils.writerVarint32(v);
        case unit64:
            return ByteUtils.writeUint64(v);
        default:
            return ByteUtils.writerString(v);
        }
    }
}
