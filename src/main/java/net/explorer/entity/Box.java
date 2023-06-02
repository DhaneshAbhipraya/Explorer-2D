package net.explorer.entity;

import net.explorer.entity.util.CollisionBox;
import net.explorer.renderer.Camera;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Box extends Entity {
    public Box() {
        this.collisionBox = new CollisionBox(this, -25, -50, 50, 50);
    }

    @Override
    public void draw(Graphics2D g2d, Camera camera) {
        super.draw(g2d, camera);
        g2d.setColor(Color.RED);
        Rectangle2D player = new Rectangle2D.Double(this.getX() - 25, this.getY() - 50, 50, 50);
        g2d.fill(player);
    }

    @Override
    public boolean canMove() {
        return false;
    }
}
