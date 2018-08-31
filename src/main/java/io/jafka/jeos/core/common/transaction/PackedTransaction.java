package io.jafka.jeos.core.common.transaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PackedTransaction {
    private List<TransactionAction> actions;
    private List<TransactionAction> contextFreeActions;
    private List<String> contextFreeData;
    
    private Integer delaySec;
    private String expiration;//"2018-08-30T02:30:49"
    private Integer maxCpuUsageMs;
    private Integer maxNetUsageWords;
    private Long refBlockNum;
    private Long refBlockPrefix;
    private List<String> signatures;
    private List<String> transactionExtensions;
    private String region;
}
