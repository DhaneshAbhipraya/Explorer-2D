package net.explorer;

import net.explorer.entity.Box;
import net.explorer.entity.Entity;
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
import java.util.ArrayList;

public class Main extends JPanel implements KeyListener {
    private static final long tps = 20;
    public static final int width = 800; // Width of the game screen
    public static final int height = 600; // Height of the game screen
    private Entity player;
    private ArrayList<Entity> entities = new ArrayList<>();
    private double moveX;
    private double moveY;
    private final Path runDir;

    public Main(String runDir) {
        System.out.println("Running Directory: "+runDir);
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
        setPreferredSize(new Dimension(width, height));
        addKeyListener(this);
        setFocusable(true);
        player = new Box();
        entities.add(player);
        for (int i = 0; i < 100; i++)
            entities.add(new Box());
    }

    public static void main(String[] args) throws InterruptedException {
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
        for (Entity entity : this.entities) {
            if (entity == this.player) entity.applyForce(this.moveX, this.moveY);
            entity.tick();
        }
        Events.getInstance().tickInitiator.endTick();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (int i = 0; i < entities.size(); i++) {
            this.entities.get(i).draw(g2d);
            this.entities.get(i).drawCollisionBox(g2d);
        }
        repaint();
    }

    public void movePlayer(double dx, double dy) {
        // Set moveX and moveY accordingly
        this.moveX += dx;
        this.moveY += dy;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT -> movePlayer(-10, 0);
            case KeyEvent.VK_RIGHT -> movePlayer(10, 0);
            case KeyEvent.VK_UP -> {
                movePlayer(0, -10);
            }
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
