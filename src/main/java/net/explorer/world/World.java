package net.explorer.world;

import net.explorer.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class World {
    private final List<Entity> entities = new ArrayList<>();

    public World() {
    }

    public void spawnEntity(Entity entity) {
        this.entities.add(entity);
    }

    public void tick() {
        for (int i = 0; i < this.entities.size(); i++) {
            this.entities.get(i).tick();
        }
    }

    public List<Entity> getEntities() {
        return this.entities;
    }
}
