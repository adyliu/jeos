package io.jafka.jeos.core.common;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * some args for signature
 * 
 * @author adyliu (imxylz@gmail.com)
 * @since 2018年9月9日
 */
@Data
public class SignArg {

    Long headBlockNum;
    Long lastIrreversibleBlockNum;
    Long refBlockPrefix;
    LocalDateTime headBlockTime;
    String chainId;
    //
    Long expiredSecond;

}
