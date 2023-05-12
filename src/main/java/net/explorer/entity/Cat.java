package net.explorer.entity;

import net.explorer.ai.tasks.Walk;
import net.explorer.entity.util.CollisionBox;
import net.explorer.entity.util.attributes.Attribute;
import net.explorer.entity.util.attributes.Attributes;

import java.awt.*;

public class Cat extends LivingEntity {

    public Cat() {
        this.collisionBox = new CollisionBox(this, -50, -50, 100, 50);
        this.useAssetDraw();
        this.attributes.add(new Attribute(Attributes.MOVEMENT_SPEED));
    }

    @Override
    public void postInit() {
        this.ai.setEnabled(true);
        this.ai.taskQueue.add(new Walk());
    }

    @Override
    public void draw(Graphics2D g2d) {
//        Image catImage = AssetsManager.getInstance().getImageFromFilePathString("entity/cat.png");
//        AffineTransform tr = new AffineTransform();
//        tr.concatenate(AffineTransform.getTranslateInstance(this.collisionBox.getX1Absolute(),this.collisionBox.getY1Absolute()));
//        tr.scale(this.collisionBox.getX2Relative()/catImage.getWidth(null),this.collisionBox.getY2Relative()/catImage.getHeight(null));
//        g2d.drawImage(catImage, tr, null);
        this.assetDraw(g2d);
    }
}
