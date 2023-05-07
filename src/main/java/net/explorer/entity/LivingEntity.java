package net.explorer.entity;

import net.explorer.ai.AI;
import net.explorer.entity.util.attributes.Attribute;
import net.explorer.entity.util.attributes.Attributes;
import net.explorer.event.TickEvent;

import java.util.ArrayList;
import java.util.List;

public abstract class LivingEntity extends Entity {
    private double health;
    protected AI ai;
    protected List<Attribute> attributes;

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
        this.attributes = new ArrayList<>();
        this.attributes.add(new Attribute(Attributes.MAX_HEALTH));
        listeners.add(new tl1(this.ai));
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
}
