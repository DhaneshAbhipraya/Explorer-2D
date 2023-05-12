package net.explorer.entity;

import net.explorer.Main;
import net.explorer.assets.AssetsManager;
import net.explorer.entity.util.Axis;
import net.explorer.entity.util.CollisionBox;
import net.explorer.event.TickEvent;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Entity {
    protected CollisionBox collisionBox = new CollisionBox(this, 0, 0, 0, 0);
    private double x;
    private double y;
    private double xVel;
    private double yVel;
    private double xAcc;
    private double yAcc;
    public TickEvent.TickInitiator tickInitiator;
    public List<TickEvent.TickListener> listeners = new ArrayList<>();
    private File assetImageFile;
    private double angle;

    public void init() {
    }

    public void postInit() {
    }

    public Entity() {
        this.x = new Random().nextDouble(Main.width);
        this.y = new Random().nextDouble(Main.height);
        this.xVel = 0;
        this.yVel = 0;
        this.xAcc = 0;
        this.yAcc = 0;
        this.setAngle(new Random().nextDouble(Math.PI * 2));
        this.tickInitiator = new TickEvent.TickInitiator();
        this.init();
        this.postInit();
    }

    public abstract void draw(Graphics2D g2d);

    public void drawCollisionBox(Graphics2D g2d) {
        g2d.setColor(Color.BLUE);
        Rectangle2D box = new Rectangle2D.Double(this.collisionBox.getX1Absolute(), this.collisionBox.getY1Absolute(), this.collisionBox.getX2Relative(), this.collisionBox.getY2Relative());
        Ellipse2D origin = new Ellipse2D.Double(x - 3, y - 3, 6, 6);
        Line2D angle = new Line2D.Double(this.getX(), this.getY(), Math.cos(this.angle) * 100 + this.getX(), Math.sin(this.angle) * 100 + this.getY());
        g2d.draw(angle);
        g2d.draw(box);
        g2d.fill(origin);
    }

    public void tick() {
        this.tickInitiator.setListeners(this.listeners);
        this.tickInitiator.startTick();
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
            this.x = Main.width - this.collisionBox.getX2Relative() + Math.abs(this.collisionBox.getX1Relative());
            this.xVel *= -0.8;
        }
        if (this.collisionBox.isCollidingAxis(Axis.X, 0)) {
            this.x = 0 - this.collisionBox.getX1Relative();
            this.xVel *= -0.8;
        }
        if (this.collisionBox.isCollidingAxis(Axis.Y, Main.height)) {
            this.y = Main.height - this.collisionBox.getY2Relative() + Math.abs(this.collisionBox.getY1Relative());
            this.yVel *= -0.8;
        }
        if (this.collisionBox.isCollidingAxis(Axis.Y, 0)) {
            this.y = 0 - this.collisionBox.getY1Relative();
            this.yVel *= -0.8;
        }

        if (this.collisionBox.getX2Absolute() > Main.width) {
            this.x = Main.width - this.collisionBox.getX2Relative() + Math.abs(this.collisionBox.getX1Relative());
            this.xVel *= -0.8;
        }
        if (this.collisionBox.getX1Absolute() < 0) {
            this.x = 0 - this.collisionBox.getX1Relative();
            this.xVel *= -0.8;
        }
        if (this.collisionBox.getY2Absolute() > Main.height) {
            this.y = Main.height - this.collisionBox.getY2Relative() + Math.abs(this.collisionBox.getY1Relative());
            this.yVel = -0.8;
        }
        if (this.collisionBox.getY1Absolute() < 0) {
            this.y = 0 - this.collisionBox.getY1Relative();
            this.yVel = -0.8;
        }
        this.tickInitiator.endTick();
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
        if (this.canMove()) {
            this.x += dx;
            this.y += dy;
        } else this.applyForce(Math.min(Math.max(dx, -1), 1), Math.min(Math.max(dy, -1), 1));
    }

    public void move(double amt) {
        if (this.canMove()) {
            move(Math.cos(this.angle) * amt, Math.sin(this.angle) * amt);
        }
    }

    public boolean canMove() {
        return false;
    }

    protected void useAssetDraw() {
        String assetImagePathString = this.getClass().getName().replaceAll("^net\\.explorer\\.", "").replaceAll("(?:(\\w+)\\.)+", "$1/").toLowerCase();
        System.out.println("Searching " + assetImagePathString);
        if (AssetsManager.getInstance().isAvailableInCache(assetImagePathString)) {
            System.out.println("File found in cache. Reusing...");
            this.assetImageFile = AssetsManager.getInstance().getPathInCache(assetImagePathString).toFile();
            return;
        }

        File assetImagePNG = Path.of(assetImagePathString + ".png").toFile();
        System.out.println("Searching " + assetImagePNG);
        if (Path.of(Main.getInstance().assetsDir + "\\" + assetImagePNG).toFile().exists()) {
            System.out.println("Image path found " + assetImagePNG);
            this.assetImageFile = assetImagePNG;
        } else {
            File assetImageJPG = Path.of(assetImagePathString + ".jpg").toFile();
            System.out.println("Searching " + assetImageJPG);
            if (Path.of(Main.getInstance().assetsDir + "\\" + assetImageJPG).toFile().exists()) {
                System.out.println("Image path found " + assetImageJPG);
                this.assetImageFile = assetImageJPG;
            } else {
                File assetImageJPEG = Path.of(assetImagePathString + ".jpeg").toFile();
                System.out.println("Searching " + assetImageJPEG);
                if (Path.of(Main.getInstance().assetsDir + "\\" + assetImageJPEG).toFile().exists()) {
                    System.out.println("Image path found " + assetImageJPEG);
                    this.assetImageFile = assetImageJPEG;
                } else {
                    System.out.println("Image not found!");
                    return;
                }
            }
        }
        AssetsManager.getInstance().addToCache(assetImagePathString, this.assetImageFile.toPath());
    }

    public void assetDraw(Graphics2D g2d) {
        if (this.assetImageFile == null) {
            this.assetImageFile = Path.of("/fallback.png").toFile();
            System.out.println("Asset image does not exist!");
            if (!Path.of(Main.getInstance().assetsDir + "\\" + this.assetImageFile.toString()).toFile().exists()) {
                System.out.println("Fallback image does not exist!");
                BufferedImage image = new BufferedImage(2, 2, BufferedImage.TYPE_INT_RGB);

                image.setRGB(1, 0, Color.MAGENTA.getRGB());
                image.setRGB(0, 1, Color.MAGENTA.getRGB());
                image.setRGB(0, 0, Color.BLACK.getRGB());
                image.setRGB(1, 1, Color.BLACK.getRGB());

                try {
                    ImageIO.write(image, "png", Path.of(Main.getInstance().assetsDir + "\\" + this.assetImageFile.toString()).toFile());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Image image = AssetsManager.getInstance().getImageFromFilePathString(assetImageFile.toString());
        AffineTransform tr = new AffineTransform();
        tr.concatenate(AffineTransform.getTranslateInstance(this.collisionBox.getX1Absolute(), this.collisionBox.getY1Absolute()));
        tr.scale(this.collisionBox.getX2Relative() / image.getWidth(null), this.collisionBox.getY2Relative() / image.getHeight(null));
        g2d.drawImage(image, tr, null);
    }

    public CollisionBox getCollisionBox() {
        return this.collisionBox;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }
}
