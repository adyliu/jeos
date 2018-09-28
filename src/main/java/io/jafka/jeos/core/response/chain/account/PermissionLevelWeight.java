package io.jafka.jeos.core.response.chain.account;

import lombok.Data;

/**
 * 
 * @author adyliu (imxylz@gmail.com)
 * @since Sep 27, 2018
 */
@Data
public class PermissionLevelWeight {
    private PermissionLevel permission;
    private Integer weight;
}
