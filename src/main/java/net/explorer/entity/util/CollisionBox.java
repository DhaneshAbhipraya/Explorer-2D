package net.explorer.entity.util;

import net.explorer.entity.Entity;

public class CollisionBox {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private final Entity entity;

    public CollisionBox(Entity entity, double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.entity = entity;
    }

    public double getX1Relative() {
        return this.x1;
    }

    public double getY1Relative() {
        return this.y1;
    }

    public double getX2Relative() {
        return this.x2;
    }

    public double getY2Relative() {
        return this.y2;
    }

    public double getX1Absolute() {
        return this.x1 + this.entity.getX();
    }

    public double getY1Absolute() {
        return this.y1 + this.entity.getY();
    }

    public double getX2Absolute() {
        return this.x2 + this.entity.getX();
    }

    public double getY2Absolute() {
        return this.y2 + this.entity.getY();
    }

    public boolean isCollidingAxis(Axis axis, double axisPos) {
        return switch (axis) {
            case X -> axisPos > this.getX1Absolute() && axisPos < this.getX2Absolute();
            case Y -> axisPos > this.getY1Absolute() && axisPos < this.getY2Absolute();
        };
    }
}
