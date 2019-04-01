import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.jafka.jeos.LocalApi;
import io.jafka.jeos.core.common.SignArg;
import io.jafka.jeos.core.request.chain.transaction.PushTransactionRequest;
import io.jafka.jeos.core.response.chain.TableRow;
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

    static void transfer(EosApi client) throws Exception {
        ObjectMapper mapper = EosApiServiceGenerator.getMapper();

        final String from = "shijiebanggg";

        // ① pack transfer data
        TransferArg transferArg = new TransferArg(from, "shijiebangmm", "0.9999 EOS", "我是中国人");
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
        packedTransaction.setRefBlockPrefix(block.getRefBlockPrefix());
        packedTransaction.setRefBlockNum(block.getBlockNum());
        // expired after 3 minutes
        String expiration = ZonedDateTime.now(ZoneId.of("GMT")).plusMinutes(3).truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        //packedTransaction.setExpiration(expiration);
        packedTransaction.setRegion("0");
        packedTransaction.setMaxNetUsageWords(0);
        packedTransaction.setMaxCpuUsageMs(0);
        packedTransaction.setActions(actions);

        // ⑥ unlock the creator's wallet
        try {
            client.unlockWallet("default", "PW5KGXiGoDXEM54YWn6yhjCmNkAwpyDemLUqRaniAwuhTArciS6j9");
        } catch (EosApiException ex) {
            System.err.println(ex.getMessage());
        }

        // ⑦ sign the transaction
        SignedPackedTransaction signedPackedTransaction = client.signTransaction(packedTransaction, //
                Arrays.asList("EOS7XP7Ks7j68Uh64HGTEeiaMsgAgKcuZbYAAf86SoPLBpxcBX5it"), //
                "038f4b0fc8ff18a4f0842a8f0564611f6e96e8535901dd45e43ac8691a1c4dca");

        System.out.println("signedPackedTransaction=" + mapper.writeValueAsString(signedPackedTransaction));
        System.out.println("\n--------------------------------\n");

        // ⑧ push the signed transaction
        PushedTransaction pushedTransaction = client.pushTransaction("none", signedPackedTransaction);
        System.out.println("pushedTransaction=" + mapper.writeValueAsString(pushedTransaction));
    }

    //    public static void main(String[] args) throws Exception {
//        BasicConfigurator.configure();
//        EosApi client = EosApiFactory.create("http://127.0.0.1:8900", "http://jungle2.cryptolions.io:80", "http://jungle2.cryptolions.io:80");
//        ;
//        // ------------------------------------------------------------------------
//        transfer(client);
//    }
    public static void main(String[] args) throws Exception{

        // --- get the current state of blockchain
        EosApi eosApi = EosApiFactory.create("http://jungle2.cryptolions.io:80");
        SignArg arg = eosApi.getSignArg(120);
        System.out.println(eosApi.getObjectMapper().writeValueAsString(arg));

        // --- sign the transation of token tansfer
//        String privateKey = "5JjmMFopR7Ha1ywAcFg5SxTTA8XbaUHuB1NappjKCiYwJXqBMYn";//replace the real private key
//        String from = "aqdapserver1";
//        String to = "qiuguochao22";
//        String quantity = "0.0010 EOS";
//        String memo = "sent by eos sdk (https://github.com/adyliu/jeos";
//        LocalApi localApi = EosApiFactory.createLocalApi();
//        PushTransactionRequest req = localApi.transfer(arg, privateKey, from, to, quantity, memo);
//        System.out.println(localApi.getObjectMapper().writeValueAsString(req));
//
//
//        // --- push the signed-transaction to the blockchain
//        PushedTransaction pts = eosApi.pushTransaction(req);
//        System.out.println(localApi.getObjectMapper().writeValueAsString(pts));
//
        TableRow result = eosApi.getTableRows("aqdapserver1","aqdapserver1","airquality",
                "8","9", "10");
        System.out.println("11");
    }

}