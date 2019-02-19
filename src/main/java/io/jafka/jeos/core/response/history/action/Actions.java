package io.jafka.jeos.core.response.history.action;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Actions {

    private List<Action> actions;

    private Long lastIrreversibleBlock;

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public Long getLastIrreversibleBlock() {
        return lastIrreversibleBlock;
    }

    @JsonProperty("last_irreversible_block")
    public void setLastIrreversibleBlock(Long lastIrreversibleBlock) {
        this.lastIrreversibleBlock = lastIrreversibleBlock;
    }
}
