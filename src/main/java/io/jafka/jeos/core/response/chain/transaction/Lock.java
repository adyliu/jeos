package io.jafka.jeos.core.response.chain.transaction;

public class Lock {

    private String scope;

    private String account;

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
