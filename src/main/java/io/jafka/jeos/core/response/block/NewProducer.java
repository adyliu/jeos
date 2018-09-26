package io.jafka.jeos.core.response.block;

import java.util.List;

import lombok.Data;

/**
 * 
 * @author adyliu (imxylz@gmail.com)
 * @since Sep 26, 2018
 */
@Data
public class NewProducer {
    private String version;
    private List<Producer> producers;
}
