package net.explorer.lister;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Listers {
    private static final Map<String, Function<Integer, ? extends Lister<?>>> listers = new HashMap<>();

    static {
        listers.put("entity", EntityLister::new);
    }

    public static Lister<?> getListerFromName(String name) {
        return listers.get(name).apply(0);
    }
}
