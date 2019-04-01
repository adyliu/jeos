import java.nio.ByteBuffer;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.jafka.jeos.LocalApi;
import io.jafka.jeos.convert.Packer;
import io.jafka.jeos.core.common.SignArg;
import io.jafka.jeos.core.request.chain.AbiJsonToBinRequest;
import io.jafka.jeos.core.request.chain.transaction.PushTransactionRequest;
import io.jafka.jeos.core.response.chain.TableRow;
import io.jafka.jeos.util.KeyUtil;
import io.jafka.jeos.util.Raw;
import org.apache.log4j.BasicConfigurator;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jafka.jeos.EosApi;
import io.jafka.jeos.EosApiFactory;
import io.jafka.jeos.core.common.transaction.PackedTransaction;
import io.jafka.jeos.core.common.transaction.SignedPackedTransaction;
import io.jafka.jeos.core.common.transaction.TransactionAction;
import io.jafka.jeos.core.common.transaction.TransactionAuthorization;
import io.jafka.jeos.core.request.chain.json2bin.TransferArg;
import io.jafka.jeos.core.response.chain.AbiJsonToBin;
import io.jafka.jeos.core.response.chain.Block;
import io.jafka.jeos.core.response.chain.transaction.PushedTransaction;
import io.jafka.jeos.exception.EosApiException;
import io.jafka.jeos.impl.EosApiServiceGenerator;

public class TransferEOS {

    static void transfer() throws Exception {
        ObjectMapper mapper = EosApiServiceGenerator.getMapper();

        final String from = "aqdapserver1";

        EosApi eosApi = EosApiFactory.create("http://jungle2.cryptolions.io:80");
        SignArg arg = eosApi.getSignArg(120);
        EosApi client = eosApi;
        // ① pack transfer data
        TransferArg transferArg = new TransferArg(from, "qiuguochao22", "0.0010 EOS", "我是中国人");
        AbiJsonToBin data = client.abiJsonToBin("eosio.token", "transfer", transferArg);
        System.out.println("bin= " + data.getBinargs());

        // ② get the latest block info
        Block block = client.getBlock(client.getChainInfo().getHeadBlockId());
        System.out.println("blockNum=" + block.getBlockNum());

        // ③ create the authorization
        List<TransactionAuthorization> authorizations = Arrays.asList(new TransactionAuthorization(from, "active"));

        // ④ build the all actions
        List<TransactionAction> actions = Arrays.asList(//
                new TransactionAction("eosio.token", "transfer", authorizations, data.getBinargs())//
        );

        // ⑤ build the packed transaction
        PackedTransaction packedTransaction = new PackedTransaction();
        packedTransaction.setExpiration(arg.getHeadBlockTime().plusSeconds(arg.getExpiredSecond()));
        packedTransaction.setRefBlockNum(arg.getLastIrreversibleBlockNum());
        packedTransaction.setRefBlockPrefix(arg.getRefBlockPrefix());

        packedTransaction.setMaxNetUsageWords(0);
        packedTransaction.setMaxCpuUsageMs(0);
        packedTransaction.setDelaySec(0);
        packedTransaction.setActions(actions);
        String privateKey = "5JjmMFopR7Ha1ywAcFg5SxTTA8XbaUHuB1NappjKCiYwJXqBMYn";
        String hash = sign(privateKey, arg, packedTransaction);
        PushTransactionRequest req = new PushTransactionRequest();
        req.setTransaction(packedTransaction);
        req.setSignatures(Arrays.asList(hash));
        LocalApi localApi = EosApiFactory.createLocalApi();
        PushedTransaction pts = eosApi.pushTransaction(req);
        System.out.println(localApi.getObjectMapper().writeValueAsString(pts));
    }

    public static void main(String[] args) throws Exception {
        //BasicConfigurator.configure();
        transfer();
    }

    private static String sign(String privateKey, SignArg arg, PackedTransaction t) {

        Raw raw = Packer.packPackedTransaction(arg.getChainId(), t);
        raw.pack(ByteBuffer.allocate(33).array());// TODO: what's this?
        String hash = KeyUtil.signHash(privateKey, raw.bytes());
        return hash;
    }

}