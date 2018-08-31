package io.jafka.jeos.core.response.chain.account;


import java.util.List;

import lombok.Data;

@Data
public class Account {

    private String accountName;
    private String coreLiquidBalance;
    private Limit cpuLimit;
    private Long cpuWeight;
    // date
    private String created;
    private String headBlockTime;
    private String lastCodeUpdate;
    //
    private Long headBlockNum;
    //
    private Limit netLimit;
    private Long netWeight;
    
    private List<Permission> permissions;
    
    private boolean privileged;
    private Long ramQuota;
    private Long ramUsage;
    
    private Object refundRequest;
    
    private Bandwidth selfDelegatedBandwidth;
    private Resource totalResources;
    private VoterInfo voterInfo;
}
