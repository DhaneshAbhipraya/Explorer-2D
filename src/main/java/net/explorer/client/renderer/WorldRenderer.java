package net.explorer.client.renderer;

import net.explorer.client.main.Explorer;
import net.explorer.entity.Player;
import net.explorer.world.World;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class WorldRenderer {
    private final World world;
    private final Camera camera;
    private AffineTransform transform;

    public WorldRenderer(World world, Camera camera) {
        this.world = world;
        this.camera = camera;
        transform = new AffineTransform();
    }

    public void render(Graphics2D g2d, Camera camera, boolean drawCollisionBox) {
        transform = new AffineTransform();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(new Color(0x333333));
        Rectangle2D backgroundRect = new Rectangle2D.Double(0, 0, g2d.getClipBounds().getWidth(), g2d.getClipBounds().getHeight());
        g2d.fill(backgroundRect);

        g2d.translate(-camera.getX() + Explorer.width / 2, -camera.getY() + Explorer.height / 2);
        transform.concatenate(AffineTransform.getTranslateInstance(-camera.getX() + Explorer.width / 2, -camera.getY() + Explorer.height / 2));
        for (int i = 0; i < this.world.getEntities().size(); i++) {
            this.world.getEntities().get(i).draw(g2d, camera);
            if (drawCollisionBox) this.world.getEntities().get(i).drawCollisionBox(g2d, camera);
            if (this.world.getEntities().get(i) instanceof Player player) {
                // player name
                g2d.setFont(new Font("", Font.PLAIN, 25));
                g2d.setColor(new Color(0x80000000, true));
                g2d.fillRect((int) (player.getX() - g2d.getFontMetrics().stringWidth("Player") / 2 - 5), (int) (player.getCollisionBox().getY1Absolute() - 10 - g2d.getFontMetrics().stringWidth("Player") / 2 + 8), g2d.getFontMetrics().stringWidth("Player") + 10, g2d.getFontMetrics().getHeight());
                g2d.setColor(Color.WHITE);
                g2d.drawString("Player", (int) (player.getX() - g2d.getFontMetrics().stringWidth("Player") / 2), (int) (player.getCollisionBox().getY1Absolute() - 10));
            }
        }
    }

    public Point convertScreenToWorld(Point point) {
        try {
            Point worldPoint = new Point(point.x, point.y);
            transform.inverseTransform(worldPoint, worldPoint);
            return worldPoint;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
