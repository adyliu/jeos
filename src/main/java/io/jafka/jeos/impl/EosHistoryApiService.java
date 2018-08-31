package io.jafka.jeos.impl;

import java.util.Map;

import io.jafka.jeos.core.request.history.TransactionRequest;
import io.jafka.jeos.core.response.history.action.Actions;
import io.jafka.jeos.core.response.history.controlledaccounts.ControlledAccounts;
import io.jafka.jeos.core.response.history.keyaccounts.KeyAccounts;
import io.jafka.jeos.core.response.history.transaction.Transaction;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface EosHistoryApiService {

    @POST("/v1/history/get_actions")
    Call<Actions> getActions(@Body Map<String, Object> requestFields);

    @POST("/v1/history/get_transaction")
    Call<Object> getTransaction(@Body TransactionRequest transactionRequest);

    @POST("/v1/history/get_key_accounts")
    Call<KeyAccounts> getKeyAccounts(@Body Map<String, String> requestFields);

    @POST("/v1/history/get_controlled_accounts")
    Call<ControlledAccounts> getControlledAccounts(@Body Map<String, String> requestFields);

}
