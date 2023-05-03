package net.explorer.entity;

import net.explorer.ai.tasks.RandomMove;
import net.explorer.entity.util.CollisionBox;

import java.awt.*;

public class Cat extends Entity {

    public Cat() {
        this.collisionBox = new CollisionBox(this,-50,-50,100,50);
    }

    @Override
    public void init() {
        this.ai.setEnabled(true);
        this.ai.taskQueue.add(new RandomMove());
    }

    @Override
    public void draw(Graphics2D g2d) {
    }
}
