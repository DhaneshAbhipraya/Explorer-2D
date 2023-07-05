package net.explorer.entity;

import net.explorer.ai.AI;
import net.explorer.client.renderer.Camera;
import net.explorer.entity.util.attributes.Attribute;
import net.explorer.entity.util.attributes.AttributeList;
import net.explorer.entity.util.attributes.Attributes;
import net.explorer.event.TickEvent;

import java.awt.*;
import java.util.List;

public abstract class LivingEntity extends Entity {
    private double health;
    protected AI ai;
    protected AttributeList attributes;

    @Override
    public void draw(Graphics2D g2d, Camera camera) {
        super.draw(g2d, camera);
    }

    public void setAIEnabled(boolean enabled) {
        if (this.ai != null)
            this.ai.setEnabled(enabled);
    }

    public AI getAi() {
        return ai;
    }

    public void setAi(AI ai) {
        this.ai = ai;
    }

    @Override
    public void init() {
        this.ai = new AI(this);
        this.health = 100.d;
        this.attributes = new AttributeList();
        this.attributes.add(new Attribute(Attributes.MAX_HEALTH));
        this.listeners.add(new tl1(this.ai));
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getHealth() {
        return this.health;
    }

    @Override
    public boolean canMove() {
        return true;
    }

    public AttributeList getAttributes() {
        return this.attributes;
    }

    public void clearAiQueue() {
        this.ai.taskQueue.clear();
    }

    public void addToAiQueue(AI.Task task) {
        this.ai.taskQueue.add(task);
    }

    public List<AI.Task> getAiQueue() {
        return this.ai.taskQueue;
    }

    public void setAiQueue(List<AI.Task> aiQueue) {
        this.ai.taskQueue = aiQueue.stream().toList();
    }

    private record tl1(AI ai) implements TickEvent.TickListener {

        @Override
        public void onStartTick() {
            this.ai.tick();
        }

        @Override
        public void onEndTick() {
        }
    }
}
