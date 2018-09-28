package io.jafka.jeos.core.common.transaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PackedTransaction {
    
    private LocalDateTime expiration;//"2018-08-30T02:30:49"
    private Long refBlockNum;
    private Long refBlockPrefix;

    private Integer maxNetUsageWords;
    private Integer maxCpuUsageMs;
    private Integer delaySec;
    private List<TransactionAction> contextFreeActions = new ArrayList<>();
    private List<TransactionAction> actions = new ArrayList<>();

    private List<String> transactionExtensions = new ArrayList<>();
    //private List<String> signatures;
    private List<String> contextFreeData = new ArrayList<>();
    
    //
    private String region;
}
