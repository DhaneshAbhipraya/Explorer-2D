package net.explorer.entity;

import net.explorer.ai.tasks.Walk;
import net.explorer.client.renderer.Camera;
import net.explorer.entity.util.AABB;
import net.explorer.entity.util.attributes.Attribute;
import net.explorer.entity.util.attributes.Attributes;

import java.awt.*;

public class Sheep extends LivingEntity {
    public Sheep() {
        this.AABB = new AABB(this, -50, -52.5, 100, 52.5);
        this.useAssetDraw();
        this.attributes.add(new Attribute(Attributes.MOVEMENT_SPEED));
    }

    @Override
    public void postInit() {
        this.ai.setEnabled(true);
        this.ai.taskQueue.add(new Walk());
    }

    @Override
    public void draw(Graphics2D g2d, Camera camera) {
        super.draw(g2d, camera);
        this.assetDraw(g2d);
    }
}
