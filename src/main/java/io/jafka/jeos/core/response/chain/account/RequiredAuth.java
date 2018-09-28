package io.jafka.jeos.core.response.chain.account;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequiredAuth {
    private Long threshold;
    private List<Key> keys = new ArrayList<>();
    private List<PermissionLevelWeight> accounts = new ArrayList<>();
    private List<Wait> waits = new ArrayList<>();
}
