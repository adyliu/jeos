package io.jafka.jeos.convert;

import java.util.Arrays;
import java.util.Collections;

import io.jafka.jeos.core.request.chain.json2bin.BuyRamArg;
import io.jafka.jeos.core.request.chain.json2bin.CreateAccountArg;
import io.jafka.jeos.core.request.chain.json2bin.DelegatebwArg;
import io.jafka.jeos.core.request.chain.json2bin.TransferArg;
import io.jafka.jeos.core.response.chain.account.Key;
import io.jafka.jeos.core.response.chain.account.PermissionLevel;
import io.jafka.jeos.core.response.chain.account.PermissionLevelWeight;
import io.jafka.jeos.core.response.chain.account.RequiredAuth;
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
        Raw raw = new Raw();
        raw.packName(arg.getCreator());
        raw.packName(arg.getName());

        Arrays.asList(arg.getOwner(), arg.getActive()).stream().filter(x -> x != null).forEach(r -> {
            // owner
            raw.packUint32(r.getThreshold());
            // ownwer.keys
            raw.packVarint32(r.getKeys().size());
            r.getKeys().forEach(k -> {
                raw.packPublicKey(k.getKey());
                raw.packUint16(k.getWeight());
            });
            // ownwer.accounts
            raw.packVarint32(r.getAccounts().size());
            r.getAccounts().forEach(a -> {
                raw.packName(a.getPermission().getActor());
                raw.packName(a.getPermission().getPermission());
                raw.packUint16(a.getWeight());
            });
            // ownwer.waits
            raw.packVarint32(r.getWaits().size());
            r.getWaits().forEach(w -> {
                raw.packUint32(w.getWeightSec());
                raw.packUint16(w.getWeight());
            });
        });
        return raw.toHex();
    }
    
    public static void main(String[] args) throws Exception {
        //System.out.println(packTransfer(new TransferArg("shijiebangmm", "womenshi1111", "1.0000 EOS", "我是中国人")));
        //System.out.println(packBuyrambytes(new BuyRamArg("shijiebanggg", "womenshi1111", 8192)));
        //System.out.println(packDelegatebw(new DelegatebwArg("shijiebanggg", "shijiebangmm", "0.1000 EOS", "0.1000 EOS", 1L)));
        PermissionLevelWeight plw = new PermissionLevelWeight();
        plw.setPermission(new PermissionLevel("shijiebangmm", "active"));
        plw.setWeight(1);
        RequiredAuth owner = new RequiredAuth(65536L, Arrays.asList(new Key("EOS7XP7Ks7j68Uh64HGTEeiaMsgAgKcuZbYAAf86SoPLBpxcBX5it",1)), Arrays.asList(plw), Collections.emptyList());
        RequiredAuth active = new RequiredAuth(1L, Arrays.asList(new Key("EOS7XP7Ks7j68Uh64HGTEeiaMsgAgKcuZbYAAf86SoPLBpxcBX5it",1)), Collections.emptyList(), Collections.emptyList());
        CreateAccountArg caa = new CreateAccountArg("shijiebanggg","womenshi1111",owner,active);
        System.out.println(packCreateAccount(caa));
    }
}
