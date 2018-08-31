package io.jafka.jeos.core.response.chain.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PushedTransaction {

    private Processed processed;
    private String transactionId;

}
