package io.jafka.jeos.core.common;

import java.util.List;
import lombok.Data;

@Data
public class Receipt {

    private Long abiSequence;

    private String actDigest;

    private List<List<String>> authSequence = null;

    private Long codeSequence;

    private Long globalSequence;

    private String receiver;

    private Long recvSequence;
}
