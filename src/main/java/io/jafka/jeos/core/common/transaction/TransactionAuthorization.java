package io.jafka.jeos.core.common.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionAuthorization {

    private String actor;
    private String permission;
}
