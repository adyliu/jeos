package io.jafka.jeos.core.response.block;

import lombok.Data;

/**
 * 
 * @author adyliu (imxylz@gmail.com)
 * @since Sep 26, 2018
 */
@Data
public class Producer {
    private String producerName;
    private String blockSigningKey;
}
