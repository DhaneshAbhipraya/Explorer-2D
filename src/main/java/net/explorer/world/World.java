package net.explorer.world;

import net.explorer.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class World {
    private final List<Entity> entities = new ArrayList<>();

    public World() {
    }

    public void spawnEntity(Entity entity) {
        entity.world = this;
        this.entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        this.entities.remove(entity);
    }

    public void tick() {
        for (int i = 0; i < this.entities.size(); i++) {
            this.entities.get(i).tick();
            for (Entity entity : this.entities) {
                if (entity != this.entities.get(i)) {
                    this.entities.get(i).handleCollisions(entity);
                }
            }
        }
    }

    public List<Entity> getEntities() {
        return this.entities;
    }
}
