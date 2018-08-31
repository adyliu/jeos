package io.jafka.jeos.impl;

import java.util.List;
import java.util.Map;

import io.jafka.jeos.core.request.chain.AbiJsonToBinRequest;
import io.jafka.jeos.core.request.chain.RequiredKeysRequest;
import io.jafka.jeos.core.request.chain.transaction.PushTransactionRequest;
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
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface EosChainApiService {

    @GET("/v1/chain/get_info")
    Call<ChainInfo> getChainInfo();

    @POST("/v1/chain/get_block")
    Call<Block> getBlock(@Body Map<String, String> requestFields);

    @POST("/v1/chain/get_account")
    Call<Account> getAccount(@Body Map<String, String> requestFields);

    @POST("/v1/chain/get_abi")
    Call<Abi> getAbi(@Body Map<String, String> requestFields);

    @POST("/v1/chain/get_code")
    Call<Code> getCode(@Body Map<String, String> requestFields);

    @POST("/v1/chain/get_table_rows")
    Call<TableRow> getTableRows(@Body Map<String, String> requestFields);

    @POST("/v1/chain/get_currency_balance")
    Call<List<String>> getCurrencyBalance(@Body Map<String, String> requestFields);

    @POST("/v1/chain/abi_json_to_bin")
    Call<AbiJsonToBin> abiJsonToBin(@Body AbiJsonToBinRequest abiJsonToBinRequest);

    @POST("/v1/chain/abi_bin_to_json")
    Call<AbiBinToJson> abiBinToJson(@Body Map<String, String> requestFields);

    @POST("/v1/chain/push_transaction")
    Call<PushedTransaction> pushTransaction(@Body PushTransactionRequest pushTransactionRequest);

    @POST("/v1/chain/push_transactions")
    Call<List<PushedTransaction>> pushTransactions(@Body List<PushTransactionRequest> pushTransactionRequests);

    @POST("/v1/chain/get_required_keys")
    Call<RequiredKeys> getRequiredKeys(@Body RequiredKeysRequest requiredKeysRequest);

    @POST("/v1/chain/get_currency_stats")
    Call<Map<String, CurrencyStats>> getCurrencyStats(@Body Map<String, String> requestFields);

}
