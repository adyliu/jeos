package io.jafka.jeos.core.common.transaction;


import java.util.List;

import lombok.Data;

@Data
public class TransactionAction {

    private String account;

    private String name;

    private List<TransactionAuthorization> authorization;

    private String data;

}
