package net.explorer.client.main;

import net.explorer.Constants;
import net.explorer.client.assets.AssetsManager;
import net.explorer.server.main.Server;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        new AssetsManager();
        Runnable runnable = () -> {
            try {
                Server.main(args);
            } catch (InterruptedException e) {
                throw new RuntimeException("Unable to launch server!");
            }
        };
        new Thread(runnable).start();
//        TickEventTest.main(args);
        Explorer game = new Explorer(args[0]);
        JFrame frame = new JFrame("Explorer 2D " + Constants.version);
        frame.add(game);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
