package net.explorer.lister;

import net.explorer.entity.*;

public class EntityLister extends Lister<Class<? extends Entity>> {
    public EntityLister(Integer index) {
        map.put("Box", Box.class);
        map.put("Cat", Cat.class);
        map.put("Player", Player.class);
        map.put("Sheep", Sheep.class);
    }
}
