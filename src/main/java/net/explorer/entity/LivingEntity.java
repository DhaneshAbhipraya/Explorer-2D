package net.explorer.entity;

import net.explorer.ai.AI;
import net.explorer.entity.util.attributes.Attribute;
import net.explorer.entity.util.attributes.AttributeList;
import net.explorer.entity.util.attributes.Attributes;
import net.explorer.event.TickEvent;
import net.explorer.renderer.Camera;

import java.awt.*;

public abstract class LivingEntity extends Entity {
    private double health;
    protected AI ai;
    protected AttributeList attributes;

    @Override
    public void draw(Graphics2D g2d, Camera camera) {
        super.draw(g2d, camera);
    }

    public void setAIEnabled(boolean enabled) {
        this.ai.setEnabled(enabled);
    }

    private class tl1 implements TickEvent.TickListener {

        private final AI ai;

        public tl1(AI ai) {
            this.ai = ai;
        }

        @Override
        public void onStartTick() {
            this.ai.tick();
        }

        @Override
        public void onEndTick() {
        }
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
}
