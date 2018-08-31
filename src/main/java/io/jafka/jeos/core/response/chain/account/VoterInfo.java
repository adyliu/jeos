package io.jafka.jeos.core.response.chain.account;

import java.util.List;

import lombok.Data;

/**
 * 
 * @author adyliu (imxylz@gmail.com)
 * @since 2018年8月29日
 */
@Data
public class VoterInfo {
    private Integer isProxy;
    private String lastVoteWeight;
    private String owner;
    private List producers;
    private String proxiedVoteWeight;
    private String proxy;
    private Long staked;
}
