package io.jafka.jeos.core.response.history.transaction;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;

@Data
public class Transaction {
    private Long cpuUsageUs;
    private Long netUsageWords;
    private String status;
    
    @JsonDeserialize(using = TrxDeserializer.class)
    // nullable
    private Trx trx;
}
