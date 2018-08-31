package io.jafka.jeos.core.response.chain.account;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Wait {

    private Integer weightSec;

    private Integer weight;
}
