package net.explorer.ai;

import net.explorer.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class AI {
    public Entity entity;
    private boolean enabled;

    public AI(Entity entity) {
        this.entity = entity;
        this.enabled = false;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public interface Task {
        void tick(Entity entity);
    }

    public void tick() {
        if (this.enabled && this.entity.canMove()) for (Task task : this.taskQueue) {
            task.tick(this.entity);
        }
    }

    public List<Task> taskQueue = new ArrayList<>();

}
