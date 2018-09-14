package io.jafka.jeos.core.request.chain.json2bin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author adyliu (imxylz@gmail.com)
 * @since 2018年9月5日
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DelegatebwArg {

    private String from;
    private String receiver;
    private String stakeNetQuantity;
    private String stakeCpuQuantity;
    // Transfer voting power and right to unstake EOS to receiver
    // 0 or 1
    private Long transfer = 0L;
}
