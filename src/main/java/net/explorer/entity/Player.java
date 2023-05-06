package net.explorer.entity;

import net.explorer.entity.util.CollisionBox;

import java.awt.*;

public class Player extends LivingEntity {
    public Player() {
        this.collisionBox = new CollisionBox(this, -25, -100, 50, 100);
        this.useAssetDraw();
    }

    @Override
    public void draw(Graphics2D g2d) {
        this.assetDraw(g2d);
    }
}
