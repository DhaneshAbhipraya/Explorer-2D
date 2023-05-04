package net.explorer.entity;

import net.explorer.Main;
import net.explorer.ai.AI;
import net.explorer.entity.util.Axis;
import net.explorer.entity.util.CollisionBox;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public abstract class Entity {
    protected CollisionBox collisionBox = new CollisionBox(this, 0, 0, 0, 0);
    private double x;
    private double y;
    private double xVel;
    private double yVel;
    private double xAcc;
    private double yAcc;
    protected AI ai;

    public void init() { }

    public Entity() {
        this.x = new Random().nextDouble(Main.width);
        this.y = new Random().nextDouble(Main.height);
        this.xVel = 0;
        this.yVel = 0;
        this.xAcc = 0;
        this.yAcc = 0;
        this.ai = new AI(this);
        this.init();
    }

    public abstract void draw(Graphics2D g2d);

    public void drawCollisionBox(Graphics2D g2d) {
        g2d.setColor(Color.BLUE);
        Rectangle2D box = new Rectangle2D.Double(this.collisionBox.getX1Absolute(), this.collisionBox.getY1Absolute(), this.collisionBox.getX2Relative(), this.collisionBox.getY2Relative());
        Ellipse2D origin = new Ellipse2D.Double(x - 3, y - 3, 6, 6);
        g2d.draw(box);
        g2d.fill(origin);
    }

    public void tick() {
        this.ai.tick();
//        this.yAcc += 9.807;
        this.xVel += this.xAcc;
        this.yVel += this.yAcc;
        this.x += this.xVel;
        this.y += this.yVel;
        this.xAcc *= 0;
        this.yAcc *= 0;
        this.xVel *= 0.5;
        this.yVel *= 0.5;
        if (this.collisionBox.isCollidingAxis(Axis.X, Main.width)) {
            this.x = Main.width - this.collisionBox.getX2Relative();
            this.xVel *= -0.8;
        }
        if (this.collisionBox.isCollidingAxis(Axis.X, 0)) {
            this.x = 0 - this.collisionBox.getX1Relative();
            this.xVel *= -0.8;
        }
        if (this.collisionBox.isCollidingAxis(Axis.Y, Main.height)) {
            this.y = Main.height - this.collisionBox.getY2Relative();
            this.yVel *= -0.8;
        }
        if (this.collisionBox.isCollidingAxis(Axis.Y, 0)) {
            this.y = 0 - this.collisionBox.getY1Relative();
            this.yVel *= -0.8;
        }

        if (this.collisionBox.getX2Absolute() > Main.width) {
            this.x = Main.width - this.collisionBox.getX2Relative();
            this.xVel *= -0.8;
        }
        if (this.collisionBox.getX1Absolute() < 0) {
            this.x = 0 - this.collisionBox.getX1Relative();
            this.xVel *= -0.8;
        }
        if (this.collisionBox.getY2Absolute() > Main.height) {
            this.y = Main.height - this.collisionBox.getY2Relative();
            this.yVel = -0.8;
        }
        if (this.collisionBox.getY1Absolute() < 0) {
            this.y = 0 - this.collisionBox.getY1Relative();
            this.yVel = -0.8;
        }
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getXVel() {
        return this.xVel;
    }

    public double getYVel() {
        return this.yVel;
    }

    public double getXAcc() {
        return this.xAcc;
    }

    public double getYAcc() {
        return this.yAcc;
    }

    public void applyForce(double fX, double fY) {
        this.xAcc += fX;
        this.yAcc += fY;
    }

    public void move(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }
}
