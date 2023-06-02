package net.explorer.client.main;

import net.explorer.Constants;
import net.explorer.client.assets.AssetsManager;
import net.explorer.client.renderer.Camera;
import net.explorer.client.renderer.WorldRenderer;
import net.explorer.entity.Box;
import net.explorer.entity.*;
import net.explorer.event.Events;
import net.explorer.event.TickEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main extends JPanel implements KeyListener {
    public static final int width = 800; // Width of the game screen
    public static final int height = 600; // Height of the game screen
    private final Entity player;
    //    private ArrayList<Entity> entities = new ArrayList<>();
    private double moveX;
    private double moveY;
    public final Path runDir;
    public final Path assetsDir;
    private static Main instance;
    private final Camera camera;
    private final WorldRenderer worldRenderer;

    public static Main getInstance() {
        return instance;
    }

    public Main(String runDir) {
        instance = this;
        new Constants().register(this);
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
        Events.getInstance().tickInitiator.addListener(new TickEvent.TickListener() {
            @Override
            public void onStartTick() {
                tick();
            }
        });
        this.camera = new Camera();
        this.worldRenderer = new WorldRenderer(net.explorer.server.main.Main.getInstance().world, camera);
        this.player = new Player();
        if (player instanceof LivingEntity livingEntity) livingEntity.setAIEnabled(false);
        net.explorer.server.main.Main.getInstance().world.spawnEntity(this.player);
        for (int i = 0; i < 10; i++) net.explorer.server.main.Main.getInstance().world.spawnEntity(new Box());
        for (int i = 0; i < 10; i++) net.explorer.server.main.Main.getInstance().world.spawnEntity(new Cat());
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            try {
                net.explorer.server.main.Main.main(args);
            } catch (InterruptedException e) {
                throw new RuntimeException("Unable to launch server!");
            }
        };
        new Thread(runnable).start();
        new AssetsManager();
//        TickEventTest.main(args);
        Main game = new Main(args[0]);
        JFrame frame = new JFrame("Explorer 2D " + Constants.version);
        frame.add(game);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void tick() {
        this.player.move(this.moveX, this.moveY);
        this.camera.setPositionFromEntity(player);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        worldRenderer.render(g2d, camera);

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
