package io.jafka.jeos.convert;

import io.jafka.jeos.core.request.chain.json2bin.CreateAccountArg;
import io.jafka.jeos.core.request.chain.json2bin.TransferArg;
import io.jafka.jeos.util.ByteArrayBuffer;
import io.jafka.jeos.util.Raw;
import io.jafka.jeos.util.ecc.Hex;

/**
 * 
 * @author adyliu (imxylz@gmail.com)
 * @since 2018年9月6日
 */
public class Packer {

    public static String packTransfer(TransferArg arg) {
        Raw raw = new Raw();
        raw.packName(arg.getFrom());
        raw.packName(arg.getTo());
        raw.packAsset(arg.getQuantity());
        raw.pack(arg.getMemo());
        return raw.toHex();
    }
    
    public static String packCreateAccount(CreateAccountArg arg) {
        ByteArrayBuffer buff = new ByteArrayBuffer(128);
        buff.append(DataType.name.encode(arg.getCreator(), TokenFunction.account));
        buff.append(DataType.name.encode(arg.getName(), TokenFunction.account));
        arg.getOwner().getKeys().stream().forEach(k ->{
            buff.append(DataType.key.encode(k.getKey(), TokenFunction.account));
        });
        arg.getActive().getKeys().stream().forEach(k ->{
            buff.append(DataType.key.encode(k.getKey(), TokenFunction.account));
        });
        return Hex.toHex(buff.toByteArray());
    }
    
    public static void main(String[] args) throws Exception {
        System.out.println(packTransfer(new TransferArg("shijiebangmm", "womenshi1111", "1.0000 EOS", "我是中国人")));
    }
}
