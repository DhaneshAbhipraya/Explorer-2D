package net.explorer;

import net.explorer.entity.Box;
import net.explorer.entity.Entity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Main extends JPanel implements KeyListener {
    private static final long tps = 20;
    public static final int width = 800; // Width of the game screen
    public static final int height = 600; // Height of the game screen
    private Entity player;
    private ArrayList<Entity> entities = new ArrayList<>();
    private double moveX;
    private double moveY;

    public Main() {
        setPreferredSize(new Dimension(width, height));
        addKeyListener(this);
        setFocusable(true);
        player = new Box();
        entities.add(player);
        for (int i = 0; i < 100; i++)
            entities.add(new Box());
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Explorer 2D");
        Main game = new Main();
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
        for (int i = 0; i < this.entities.size(); i++) {
            if (this.entities.get(i) == this.player) this.entities.get(i).applyForce(this.moveX, this.moveY);
            this.entities.get(i).tick();
        }
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
