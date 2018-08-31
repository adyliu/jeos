package io.jafka.jeos.core.response.chain;

import lombok.Data;

@Data
public class ChainInfo {

    private Long blockCpuLimit;
    private Long blockNetLimit;
    private String chainId;
    private String headBlockId;
    private Long headBlockNum;
    private String headBlockProducer;
    private String headBlockTime;
    private String lastIrreversibleBlockId;
    private Long lastIrreversibleBlockNum;
    private String serverVersion;
    private String serverVersionString;
    private Long virtualBlockCpuLimit;
    private Long virtualBlockNetLimit;
}
