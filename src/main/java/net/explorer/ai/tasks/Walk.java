package net.explorer.ai.tasks;

import net.explorer.ai.AI;
import net.explorer.entity.Entity;

import java.util.Random;

public class Walk implements AI.Task {
    @Override
    public void tick(Entity entity) {
        Random random = new Random();
        if (random.nextBoolean()) entity.move(3);
        if (random.nextBoolean()) {
            entity.setAngle(entity.getAngle() + random.nextDouble(-Math.PI / 4, Math.PI / 4));
        }
    }
}
