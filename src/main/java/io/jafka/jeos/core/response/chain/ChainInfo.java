package io.jafka.jeos.core.response.chain;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ChainInfo {

    private Long blockCpuLimit;
    private Long blockNetLimit;
    private String chainId;
    private String headBlockId;
    private Long headBlockNum;
    private String headBlockProducer;
    
    //@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime headBlockTime;
    
    private String lastIrreversibleBlockId;
    private Long lastIrreversibleBlockNum;
    private String serverVersion;
    private String serverVersionString;
    private Long virtualBlockCpuLimit;
    private Long virtualBlockNetLimit;
}
