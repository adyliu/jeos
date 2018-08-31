package io.jafka.jeos.core.response.history.transaction;

import java.util.List;

import io.jafka.jeos.core.common.Action;
import lombok.Data;

/**
 * 
 * @author adyliu (imxylz@gmail.com)
 * @since 2018年8月29日
 */
@Data
public class TransactionItem {

    private Long delaySec;
    private String expiration;
    private Long maxCpuUsageMs;
    private Long maxNetUsageWords;
    private Long refBlockNum;
    private Long refBlockPrefix;
    private List<Action> actions;
    private List<Action> contextFreeActions;
}
