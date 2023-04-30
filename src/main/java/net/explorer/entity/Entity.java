package net.explorer.entity;

public abstract class Entity {
    private double x;
    private double y;
    private double xVel;
    private double yVel;
    private double xAcc;
    private double yAcc;
    public Entity() {
        this.x = 0;
        this.y = 0;
        this.xVel = 0;
        this.yVel = 0;
        this.xAcc = 0;
        this.yAcc = 0;
    }
    public void tick() {
        this.xVel += this.xAcc;
        this.yVel += this.yAcc;
        this.x += this.xVel;
        this.y += this.yVel;
        this.xAcc *= 0;
        this.yAcc *= 0;
        this.xVel *= 0.8;
        this.yVel *= 0.8;
    }
}
