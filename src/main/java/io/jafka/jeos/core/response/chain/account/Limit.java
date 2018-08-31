package io.jafka.jeos.core.response.chain.account;

import lombok.Data;

/**
 * 
 * @author adyliu (imxylz@gmail.com)
 * @since 2018年8月29日
 */
@Data
public class Limit {
    private Long available;
    private Long max;
    private Long used;
}
