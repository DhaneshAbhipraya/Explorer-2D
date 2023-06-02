package net.explorer.server.main;

import net.explorer.event.Events;
import net.explorer.server.GameLogic;
import net.explorer.world.World;

import static net.explorer.Constants.tps;

public class Main {
    private static Main instance;
    private final GameLogic gameLogic;
    public World world;

    private Main() throws InterruptedException {
        instance = this;
        world = new World();
        gameLogic = new GameLogic();
        while (true) {
            gameLogic.tick();
            Thread.sleep(1000L / tps);
        }
    }

    public static Main getInstance() {
        return instance;
    }

    public static void main(String[] args) throws InterruptedException {
        new Events();
        Main server = new Main();
    }
}
