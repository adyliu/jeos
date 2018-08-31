package io.jafka.jeos.core.response.chain.transaction;

import lombok.Data;

/**
 * 
 * @author adyliu (imxylz@gmail.com)
 * @since 2018年8月30日
 */
@Data
public class Receipt {

    private Long cpuUsageUs;
    private Integer netUsageWords;
    private String status;
}
