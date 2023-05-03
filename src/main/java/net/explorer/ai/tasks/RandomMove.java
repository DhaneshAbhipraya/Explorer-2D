package net.explorer.ai.tasks;

import net.explorer.ai.AI;
import net.explorer.entity.Entity;

import java.util.Random;

public class RandomMove implements AI.Task {
    @Override
    public void tick(Entity entity) {
        Random random = new Random();
        entity.move(random.nextDouble(4) - 2, random.nextDouble(4) - 2);
    }
}
