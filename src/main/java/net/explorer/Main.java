package net.explorer;

import net.explorer.assets.AssetsManager;
import net.explorer.entity.Box;
import net.explorer.entity.Entity;
import net.explorer.entity.Player;
import net.explorer.event.Events;
import net.explorer.world.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main extends JPanel implements KeyListener {
    private static final long tps = 20;
    public static final int width = 800; // Width of the game screen
    public static final int height = 600; // Height of the game screen
    private final Entity player;
    //    private ArrayList<Entity> entities = new ArrayList<>();
    private final World world;
    private double moveX;
    private double moveY;
    public final Path runDir;
    public final Path assetsDir;
    private static Main instance;

    public static Main getInstance() {
        return instance;
    }

    public Main(String runDir) {
        instance = this;
        System.out.println("Running Directory: " + runDir);
        File file = Path.of(runDir, "runDirRoot").toFile();
        Path path = Path.of(runDir);
        this.runDir = path;
        if (!file.exists()) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Running Directory File does not exist!");
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        this.assetsDir = Path.of(this.runDir.toString(), "assets");
        try {
            Files.createDirectories(this.assetsDir);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Assets Directory: " + this.assetsDir);

        AssetsManager.getInstance().setMain(this);

        setPreferredSize(new Dimension(width, height));
        addKeyListener(this);
        setFocusable(true);
        this.world = new World();
        this.player = new Player();
        this.world.spawnEntity(this.player);
        for (int i = 0; i < 100; i++)
            this.world.spawnEntity(new Box());
    }

    public static void main(String[] args) throws InterruptedException {
        new AssetsManager();
        new Events();
//        TickEventTest.main(args);
        JFrame frame = new JFrame("Explorer 2D");
        Main game = new Main(args[0]);
        frame.add(game);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        while (true) {
            game.tick();
            Thread.sleep(1000L / tps);
        }
    }

    public void tick() {
        Events.getInstance().tickInitiator.startTick();
        this.player.move(this.moveX, this.moveY);
        this.world.tick();
        Events.getInstance().tickInitiator.endTick();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (int i = 0; i < this.world.getEntities().size(); i++) {
            this.world.getEntities().get(i).draw(g2d);
            this.world.getEntities().get(i).drawCollisionBox(g2d);
        }

        this.player.draw(g2d);
        this.player.drawCollisionBox(g2d);

        // player name
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 25));
        g2d.drawString("Player", (int) this.player.getX(), (int) this.player.getCollisionBox().getY2Absolute());

        repaint();
    }

    public void movePlayer(double dx, double dy) {
        // Set moveX and moveY accordingly
        if (dx != 0) this.moveX = dx;
        if (dy != 0) this.moveY = dy;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT -> movePlayer(-10, 0);
            case KeyEvent.VK_RIGHT -> movePlayer(10, 0);
            case KeyEvent.VK_UP -> movePlayer(0, -10);
            case KeyEvent.VK_DOWN -> movePlayer(0, 10);
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
            this.moveX = 0;
        }
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            this.moveY = 0;
        }
    }
}
