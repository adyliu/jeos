package io.jafka.jeos.impl;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jafka.jeos.EosApi;
import io.jafka.jeos.core.common.SignArg;
import io.jafka.jeos.core.common.WalletKeyType;
import io.jafka.jeos.core.common.transaction.PackedTransaction;
import io.jafka.jeos.core.common.transaction.SignedPackedTransaction;
import io.jafka.jeos.core.request.chain.AbiJsonToBinRequest;
import io.jafka.jeos.core.request.chain.RequiredKeysRequest;
import io.jafka.jeos.core.request.chain.transaction.PushTransactionRequest;
import io.jafka.jeos.core.request.history.TransactionRequest;
import io.jafka.jeos.core.request.wallet.transaction.SignTransactionRequest;
import io.jafka.jeos.core.response.chain.AbiBinToJson;
import io.jafka.jeos.core.response.chain.AbiJsonToBin;
import io.jafka.jeos.core.response.chain.Block;
import io.jafka.jeos.core.response.chain.ChainInfo;
import io.jafka.jeos.core.response.chain.RequiredKeys;
import io.jafka.jeos.core.response.chain.TableRow;
import io.jafka.jeos.core.response.chain.abi.Abi;
import io.jafka.jeos.core.response.chain.account.Account;
import io.jafka.jeos.core.response.chain.code.Code;
import io.jafka.jeos.core.response.chain.currencystats.CurrencyStats;
import io.jafka.jeos.core.response.chain.transaction.PushedTransaction;
import io.jafka.jeos.core.response.history.action.Actions;
import io.jafka.jeos.core.response.history.controlledaccounts.ControlledAccounts;
import io.jafka.jeos.core.response.history.keyaccounts.KeyAccounts;
import io.jafka.jeos.core.response.history.transaction.Transaction;
import io.jafka.jeos.util.KeyUtil;

public class EosApiRestClientImpl implements EosApi {

    private final EosWalletApiService eosWalletApiService;

    private final EosChainApiService eosChainApiService;

    private final EosHistoryApiService eosHistoryApiService;

    public EosApiRestClientImpl(String baseUrl){
        eosWalletApiService = EosApiServiceGenerator.createService(EosWalletApiService.class, baseUrl);
        eosChainApiService = EosApiServiceGenerator.createService(EosChainApiService.class, baseUrl);
        eosHistoryApiService = EosApiServiceGenerator.createService(EosHistoryApiService.class, baseUrl);
    }

    public EosApiRestClientImpl(String baseUrl, String bearerToken){
        eosWalletApiService = EosApiServiceGenerator.createService(EosWalletApiService.class, baseUrl, bearerToken);
        eosChainApiService = EosApiServiceGenerator.createService(EosChainApiService.class, baseUrl, bearerToken);
        eosHistoryApiService = EosApiServiceGenerator.createService(EosHistoryApiService.class, baseUrl, bearerToken);
    }

    public EosApiRestClientImpl(String walletBaseUrl, String chainBaseUrl, String historyBaseUrl){
        eosWalletApiService = EosApiServiceGenerator.createService(EosWalletApiService.class, walletBaseUrl);
        eosChainApiService = EosApiServiceGenerator.createService(EosChainApiService.class, chainBaseUrl);
        eosHistoryApiService = EosApiServiceGenerator.createService(EosHistoryApiService.class, historyBaseUrl);
    }

    public EosApiRestClientImpl(String walletBaseUrl, String chainBaseUrl, String historyBaseUrl, String bearerToken){
        eosWalletApiService = EosApiServiceGenerator.createService(EosWalletApiService.class, walletBaseUrl, bearerToken);
        eosChainApiService = EosApiServiceGenerator.createService(EosChainApiService.class, chainBaseUrl, bearerToken);
        eosHistoryApiService = EosApiServiceGenerator.createService(EosHistoryApiService.class, historyBaseUrl, bearerToken);
    }

    @Override
    public ChainInfo getChainInfo(){
        return EosApiServiceGenerator.executeSync(eosChainApiService.getChainInfo());
    }

    @Override
    public Block getBlock(String blockNumberOrId){
        return EosApiServiceGenerator.executeSync(eosChainApiService.getBlock(Collections.singletonMap("block_num_or_id", blockNumberOrId)));
    }

    @Override
    public Account getAccount(String accountName){
        return EosApiServiceGenerator.executeSync(eosChainApiService.getAccount(Collections.singletonMap("account_name", accountName)));
    }

    @Override
    public Abi getAbi(String accountName){
        return EosApiServiceGenerator.executeSync(eosChainApiService.getAbi(Collections.singletonMap("account_name", accountName)));
    }

    @Override
    public Code getCode(String accountName){
        return EosApiServiceGenerator.executeSync(eosChainApiService.getCode(Collections.singletonMap("account_name", accountName)));
    }

    @Override
    public TableRow getTableRows(String scope, String code, String table){
        LinkedHashMap<String, String> requestParameters = new LinkedHashMap<>(7);

        requestParameters.put("scope", scope);
        requestParameters.put("code", code);
        requestParameters.put("table", table);
        requestParameters.put("json", "true");

        return EosApiServiceGenerator.executeSync(eosChainApiService.getTableRows(requestParameters));
    }

    @Override
    public List<String> getCurrencyBalance(String code, String accountName, String symbol){
        LinkedHashMap<String, String> requestParameters = new LinkedHashMap<>(3);

        requestParameters.put("code", code);
        requestParameters.put("account", accountName);
        requestParameters.put("symbol", symbol);

        return EosApiServiceGenerator.executeSync(eosChainApiService.getCurrencyBalance(requestParameters));
    }

    @Override
    public AbiBinToJson abiBinToJson(String code, String action, String binargs){
        LinkedHashMap<String, String> requestParameters = new LinkedHashMap<>(3);

        requestParameters.put("code", code);
        requestParameters.put("action", action);
        requestParameters.put("binargs", binargs);

        return EosApiServiceGenerator.executeSync(eosChainApiService.abiBinToJson(requestParameters));
    }

    @Override
    public <T> AbiJsonToBin abiJsonToBin(String code, String action, T args) {
        return EosApiServiceGenerator.executeSync(eosChainApiService.abiJsonToBin(new AbiJsonToBinRequest(code, action, args)));
    }

    @Override
    public PushedTransaction pushTransaction(String compression, SignedPackedTransaction packedTransaction){
        return EosApiServiceGenerator.executeSync(eosChainApiService.pushTransaction(new PushTransactionRequest(compression, packedTransaction, packedTransaction.getSignatures())));
    }
    @Override
    public PushedTransaction pushTransaction(PushTransactionRequest pushTransactionRequest) {
        return EosApiServiceGenerator.executeSync(eosChainApiService.pushTransaction(pushTransactionRequest));
    }

    @Override
    public List<PushedTransaction> pushTransactions(List<PushTransactionRequest> pushTransactionRequests){
        return EosApiServiceGenerator.executeSync(eosChainApiService.pushTransactions(pushTransactionRequests));
    }

    @Override
    public RequiredKeys getRequiredKeys(PackedTransaction transaction, List<String> keys){
        return EosApiServiceGenerator.executeSync(eosChainApiService.getRequiredKeys(new RequiredKeysRequest(transaction, keys)));
    }

    @Override
    public Map<String, CurrencyStats> getCurrencyStats(String code, String symbol){
        LinkedHashMap<String, String> requestParameters = new LinkedHashMap<>(2);

        requestParameters.put("code", code);
        requestParameters.put("symbol", symbol);

        return EosApiServiceGenerator.executeSync(eosChainApiService.getCurrencyStats(requestParameters));
    }

    @Override
    public String createWallet(String walletName){
        return EosApiServiceGenerator.executeSync(eosWalletApiService.createWallet(walletName));
    }

    @Override
    public void openWallet(String walletName){
        EosApiServiceGenerator.executeSync(eosWalletApiService.openWallet(walletName));
    }

    @Override
    public void lockWallet(String walletName){
        EosApiServiceGenerator.executeSync(eosWalletApiService.lockWallet(walletName));
    }

    @Override
    public void lockAllWallets(){
        EosApiServiceGenerator.executeSync(eosWalletApiService.lockAll());
    }

    @Override
    public void unlockWallet(String walletName, String walletPassword){
        List<String> requestFields = new ArrayList<>(2);

        requestFields.add(walletName);
        requestFields.add(walletPassword);
        EosApiServiceGenerator.executeSync(eosWalletApiService.unlockWallet(requestFields));
    }

    @Override
    public void importKeyIntoWallet(String walletName, String key){
        List<String> requestFields = new ArrayList<>(2);

        requestFields.add(walletName);
        requestFields.add(key);
        EosApiServiceGenerator.executeSync(eosWalletApiService.importKey(requestFields));
    }

    @Override
    public List<String> listWallets(){
        return EosApiServiceGenerator.executeSync(eosWalletApiService.listWallets());
    }

    @Override
    public List<List<String>> listKeys(String walletName, String password){
        List<String> requestFields = Arrays.asList(walletName, password);
       return EosApiServiceGenerator.executeSync(eosWalletApiService.listKeys(requestFields));
    }

    @Override
    public List<String> getPublicKeys(){
        return EosApiServiceGenerator.executeSync(eosWalletApiService.getPublicKeys());
    }

    @Override
    public SignedPackedTransaction signTransaction(PackedTransaction packedTransaction, List<String> publicKeys, String chainId) {
        return EosApiServiceGenerator.executeSync(eosWalletApiService.signTransaction(new SignTransactionRequest(packedTransaction, publicKeys, chainId)));
    }

    @Override
    public void setWalletTimeout(Integer timeout){
        EosApiServiceGenerator.executeSync(eosWalletApiService.setTimeout(timeout));
    }

    @Override
    public String signDigest(String digest, String publicKey){
        return EosApiServiceGenerator.executeSync(eosWalletApiService.signDigest(Arrays.asList(digest, publicKey)));
    }

    @Override
    public String createKey(String walletName, WalletKeyType keyType){
        return EosApiServiceGenerator.executeSync(eosWalletApiService.createKey(Arrays.asList(walletName, keyType.name())));
    }

    @Override
    public Actions getActions(String accountName, Integer pos, Integer offset){
        LinkedHashMap<String, Object> requestParameters = new LinkedHashMap<>(3);

        requestParameters.put("account_name", accountName);
        requestParameters.put("pos", pos);
        requestParameters.put("offset", offset);

        return EosApiServiceGenerator.executeSync(eosHistoryApiService.getActions(requestParameters));
    }

    @Override
    public Object getTransaction(TransactionRequest transactionRequest){
        return EosApiServiceGenerator.executeSync(eosHistoryApiService.getTransaction(transactionRequest));
    }

    @Override
    public KeyAccounts getKeyAccounts(String publicKey){
        LinkedHashMap<String, String> requestParameters = new LinkedHashMap<>(1);

        requestParameters.put("public_key", publicKey);

        return EosApiServiceGenerator.executeSync(eosHistoryApiService.getKeyAccounts(requestParameters));
    }

    @Override
    public ControlledAccounts getControlledAccounts(String controllingAccountName){
        LinkedHashMap<String, String> requestParameters = new LinkedHashMap<>(1);

        requestParameters.put("controlling_account", controllingAccountName);

        return EosApiServiceGenerator.executeSync(eosHistoryApiService.getControlledAccounts(requestParameters));
    }

    @Override
    public ObjectMapper getObjectMapper() {
        return EosApiServiceGenerator.getMapper();
    }
    
    @Override
    public SignArg getSignArg(int expiredSecond) {
        ChainInfo info = getChainInfo();
        Block block = getBlock(info.getLastIrreversibleBlockNum().toString());
        
        SignArg arg = new SignArg();
        arg.setChainId(info.getChainId());
        arg.setExpiredSecond(expiredSecond);
        arg.setHeadBlockNum(info.getHeadBlockNum());
        arg.setHeadBlockTime(info.getHeadBlockTime());
        arg.setLastIrreversibleBlockNum(info.getLastIrreversibleBlockNum());
        arg.setRefBlockPrefix(block.getRefBlockPrefix());
        return arg;
    }
    
    // ------------------------------------------------------------------------------
    //                                                                              //
    //                                  LOCAL API                                   //
    //                                                                              //
    // ------------------------------------------------------------------------------
    
}
