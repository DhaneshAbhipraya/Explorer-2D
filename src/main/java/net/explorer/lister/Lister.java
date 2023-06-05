package net.explorer.lister;

import java.util.ArrayList;
import java.util.List;

public abstract class Lister<T> {
    protected List<T> list = new ArrayList<>();

    public List<T> toList() {
        return list;
    }
}
