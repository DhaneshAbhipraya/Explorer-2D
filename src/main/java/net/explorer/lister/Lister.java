package net.explorer.lister;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Lister<T> {
    protected Map<String, T> map = new HashMap<>();

    public List<T> toList() {
        return map.values().stream().toList();
    }

    public T fromKey(String key) {
        return map.get(key);
    }

    public List<String> getKeys() {
        return map.keySet().stream().toList();
    }
}
