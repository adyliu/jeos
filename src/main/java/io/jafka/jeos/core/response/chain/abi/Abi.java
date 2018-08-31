package io.jafka.jeos.core.response.chain.abi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Abi {

    private String accountName;

    private io.jafka.jeos.core.response.chain.code.Abi abi;


    public String getAccountName() {
        return accountName;
    }

    @JsonProperty("account_name")
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public io.jafka.jeos.core.response.chain.code.Abi getAbi() {
        return abi;
    }

    public void setAbi(io.jafka.jeos.core.response.chain.code.Abi abi) {
        this.abi = abi;
    }

}
