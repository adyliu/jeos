package io.jafka.jeos.core.response.chain.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Key {
    private String key;
    private Integer weight;
}
