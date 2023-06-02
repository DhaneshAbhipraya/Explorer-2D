package net.explorer.renderer;

import net.explorer.Main;
import net.explorer.entity.Player;
import net.explorer.world.World;

import java.awt.*;

public class WorldRenderer {
    private final World world;
    private final Camera camera;

    public WorldRenderer(World world, Camera camera) {
        this.world = world;
        this.camera = camera;
    }

    public void render(Graphics2D g2d, Camera camera) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.translate(-camera.getX() + Main.width / 2, -camera.getY() + Main.height / 2);
        for (int i = 0; i < this.world.getEntities().size(); i++) {
            this.world.getEntities().get(i).draw(g2d, camera);
            this.world.getEntities().get(i).drawCollisionBox(g2d, camera);
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
}
