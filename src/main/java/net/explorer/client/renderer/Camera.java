package net.explorer.client.renderer;

import net.explorer.entity.Entity;

public class Camera {
    private double x = 0;
    private double y = 0;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setPositionFromEntity(Entity entity) {
        setX(entity.getX());
        setY(entity.getY());
    }
}
