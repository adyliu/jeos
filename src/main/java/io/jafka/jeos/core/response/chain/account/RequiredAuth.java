package io.jafka.jeos.core.response.chain.account;

import java.util.List;

import lombok.Data;

@Data
public class RequiredAuth {

    private List<String> accounts;

    private List<Key> keys;

    private Long threshold;

    private List<Wait> waits;
}
