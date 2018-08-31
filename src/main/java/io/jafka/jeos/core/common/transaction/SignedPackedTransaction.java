package io.jafka.jeos.core.common.transaction;


import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class SignedPackedTransaction extends PackedTransaction {

    private List<String> signatures;

}
