package net.explorer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;

public class Main extends JPanel implements KeyListener {
    private final int width = 800; // Width of the game screen
    private final int height = 600; // Height of the game screen
    private int playerX = 0; // X coordinate of the player
    private int playerY = 0; // Y coordinate of the player

    public Main() {
        setPreferredSize(new Dimension(width, height));
        addKeyListener(this);
        setFocusable(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Animal Explorer");
        Main game = new Main();
        frame.add(game);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the player
        g2d.setColor(Color.RED);
        Rectangle2D player = new Rectangle2D.Double(playerX, playerY, 50, 50);
        g2d.fill(player);
    }

    public void movePlayer(int dx, int dy) {
        // Move the player by dx and dy
        playerX += dx;
        playerY += dy;
        repaint();
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
    }
}
