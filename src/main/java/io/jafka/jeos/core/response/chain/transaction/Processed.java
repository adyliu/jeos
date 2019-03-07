package io.jafka.jeos.core.response.chain.transaction;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.jafka.jeos.core.common.ActionTrace;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Processed {

    private List<ActionTrace> actionTraces;
    private Integer elapsed;
    private String except;
    private String id;
    private Long netUsage;
    private Receipt receipt;
    private Boolean scheduled;
    
/*    
    private List<String> deferredTransactionRequests;

    private String cpuUsage;

    private String status;

    private List<Lock> writeLocks;

    private String regionId;

    private String netUsageWords;

    private String sharedIndex;

    

    private String cycleIndex;

    private String setupProfilingUs;

    private String profilingUs;


    private String packedTrxDigest;

    private List<Lock> readLocks;*/

}
