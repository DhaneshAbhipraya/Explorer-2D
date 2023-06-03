package net.explorer.server.main;

import net.explorer.event.Events;
import net.explorer.server.GameLogic;
import net.explorer.world.World;

import static net.explorer.Constants.tps;

public class Server {
    private static Server instance;
    private final GameLogic gameLogic;
    public World world;

    private Server() throws InterruptedException {
        instance = this;
        world = new World();
        gameLogic = new GameLogic();
        while (true) {
            gameLogic.tick();
            Thread.sleep(1000L / tps);
        }
    }

    public static Server getInstance() {
        return instance;
    }

    public static void main(String[] args) throws InterruptedException {
        new Events();
        Server server = new Server();
    }
}
