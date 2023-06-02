package net.explorer.entity;

import net.explorer.entity.util.CollisionBox;
import net.explorer.renderer.Camera;

import java.awt.*;

public class Player extends LivingEntity {
    public Player() {
        this.collisionBox = new CollisionBox(this, -25, -100, 50, 100);
        this.useAssetDraw();
    }

    @Override
    public void draw(Graphics2D g2d, Camera camera) {
        super.draw(g2d, camera);
        this.assetDraw(g2d);
    }
}
