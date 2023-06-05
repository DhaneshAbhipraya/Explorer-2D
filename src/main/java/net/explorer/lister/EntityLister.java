package net.explorer.lister;

import net.explorer.entity.*;

public class EntityLister extends Lister<Class<? extends Entity>> {
    public EntityLister(Integer index) {
        list.add(Box.class);
        list.add(Cat.class);
        list.add(Player.class);
        list.add(Sheep.class);
    }
}
