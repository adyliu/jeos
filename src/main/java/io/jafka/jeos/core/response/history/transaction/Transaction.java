package io.jafka.jeos.core.response.history.transaction;

import lombok.Data;

@Data
public class Transaction {
    private Long cpuUsageUs;
    private Long netUsageWords;
    private String status;
    private Trx trx;
}
