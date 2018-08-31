
package io.jafka.jeos.core.common;

import lombok.Data;

@Data
public class Authorization {

    private String actor;
    private String permission;

}
