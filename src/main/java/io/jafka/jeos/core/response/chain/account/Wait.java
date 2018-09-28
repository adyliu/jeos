package io.jafka.jeos.core.response.chain.account;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Wait {

    private Long weightSec;
    private Integer weight;
}
