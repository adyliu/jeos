package io.jafka.jeos;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jafka.jeos.core.common.WalletKeyType;
import io.jafka.jeos.core.common.transaction.PackedTransaction;
import io.jafka.jeos.core.common.transaction.SignedPackedTransaction;
import io.jafka.jeos.core.request.chain.transaction.PushTransactionRequest;
import io.jafka.jeos.core.request.history.TransactionRequest;
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

public interface EosApi {

    ChainInfo getChainInfo();

    Block getBlock(String blockNumberOrId);

    Account getAccount(String accountName);

    Abi getAbi(String accountName);

    Code getCode(String accountName);

    TableRow getTableRows(String scope, String code, String table);

    List<String> getCurrencyBalance(String code, String accountName, String symbol);

    AbiBinToJson abiBinToJson(String code, String action, String binargs);

    <T> AbiJsonToBin abiJsonToBin(String code, String action, T args);

    PushedTransaction pushTransaction(String compression, SignedPackedTransaction packedTransaction);

    List<PushedTransaction> pushTransactions(List<PushTransactionRequest> pushTransactionRequests);

    RequiredKeys getRequiredKeys(PackedTransaction transaction, List<String> keys);

    Map<String, CurrencyStats> getCurrencyStats(String code, String symbol);

    String createWallet(String walletName);

    void openWallet(String walletName);

    void lockWallet(String walletName);

    void lockAllWallets();

    void unlockWallet(String walletName, String walletPassword);

    void importKeyIntoWallet(String walletName, String walletKey);

    List<String> listWallets();

    List<List<String>> listKeys(String walletName, String password);

    List<String> getPublicKeys();

    SignedPackedTransaction signTransaction(PackedTransaction unsignedTransaction, List<String> publicKeys, String chainId);

    void setWalletTimeout(Integer timeout);

    String signDigest(String digest, String publicKey);

    String createKey(String walletName, WalletKeyType walletKeyType);

    Actions getActions(String accountName, Integer pos, Integer offset);
    
    //TODO: changed day by day
    Object getTransaction(TransactionRequest transactionRequest);

    KeyAccounts getKeyAccounts(String publicKey);

    ControlledAccounts getControlledAccounts(String controllingAccountName);

    ObjectMapper getObjectMapper();
}
