package io.jafka.jeos.core.response.chain.account;

import lombok.Data;

/**
 * 
 * @author adyliu (imxylz@gmail.com)
 * @since 2018年8月29日
 */
@Data
public class Resource {
    private String cpuWeight;
    private String netWeight;
    private String owner;
    private Long ramBytes;
}
