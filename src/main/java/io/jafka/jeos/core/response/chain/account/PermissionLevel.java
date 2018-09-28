package io.jafka.jeos.core.response.chain.account;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 
 * @author adyliu (imxylz@gmail.com)
 * @since Sep 27, 2018
 */
@Data
@AllArgsConstructor
public class PermissionLevel {
    private String actor;
    private String permission;
}
