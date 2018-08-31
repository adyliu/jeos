package io.jafka.jeos.core.response.chain.account;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Permission {

    private String parent;
    private String permName;
    private RequiredAuth requiredAuth;
}
