package io.jafka.jeos.convert;

import io.jafka.jeos.core.request.chain.json2bin.BuyRamArg;
import io.jafka.jeos.core.request.chain.json2bin.CreateAccountArg;
import io.jafka.jeos.core.request.chain.json2bin.DelegatebwArg;
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
    public static String packBuyrambytes(BuyRamArg arg) {
        Raw raw = new Raw();
        raw.packName(arg.getPayer());
        raw.packName(arg.getReceiver());
        raw.pack(arg.getBytes());
        return raw.toHex();
    }
    public static String packDelegatebw(DelegatebwArg arg) {
        Raw raw = new Raw();
        raw.packName(arg.getFrom());
        raw.packName(arg.getReceiver());
        raw.packAsset(arg.getStakeNetQuantity());
        raw.packAsset(arg.getStakeCpuQuantity());
        raw.packVarint32(arg.getTransfer());
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
        System.out.println(packBuyrambytes(new BuyRamArg("shijiebanggg", "womenshi1111", 8192)));
        System.out.println(packDelegatebw(new DelegatebwArg("shijiebanggg", "shijiebangmm", "0.1000 EOS", "0.1000 EOS", 1L)));
    }
}
