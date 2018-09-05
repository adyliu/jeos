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
public class BuyRamArg {
    private String payer;
    private String receiver;
    private int bytes;
}
