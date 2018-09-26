package io.jafka.jeos.core.response.chain;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.jafka.jeos.core.response.block.NewProducer;
import io.jafka.jeos.core.response.history.transaction.Transaction;
import lombok.Data;

@Data
public class Block {

    private String actionMroot;
    private Long blockNum;
    private Integer confirmed;
    private String id;
    private String previous;
    private String producer;
    private String producerSignature;
    private Long refBlockPrefix;
    private Long scheduleVersion;
    private LocalDateTime timestamp;
    private String transactionMroot;

    private String actionMerkleRoot;

    private String blockMerkleRoot;

    private NewProducer newProducers;

    private Transaction[] transactions;

    private String[] headerExtensions;

    private String[] blockExtensions;
}
