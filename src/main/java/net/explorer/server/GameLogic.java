package net.explorer.server;

import net.explorer.event.Events;
import net.explorer.server.main.Main;

public class GameLogic {

    public void tick() {
        Events.getInstance().tickInitiator.startTick();
//        this.player.move(this.moveX, this.moveY);
        Main.getInstance().world.tick();
//        this.camera.setPositionFromEntity(player);
        Events.getInstance().tickInitiator.endTick();
    }

}
