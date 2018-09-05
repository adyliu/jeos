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
@NoArgsConstructor
@AllArgsConstructor
public class TransferArg {
    private String from;
    private String to;
    private String quantity;
    private String memo;
}
