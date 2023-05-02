package net.explorer;

import net.explorer.event.Events;
import net.explorer.event.TickEvent;

public class TickEventTest {
    public static void main(String[] args) {
        TickEvent.TickListener tickListener = new TickEvent.TickListener() {
            @Override
            public void onStartTick() {
                System.out.println("Run from start tick!");
            }

            @Override
            public void onEndTick() {
                System.out.println("Run from end tick!");
            }
        };
        Events.getInstance().tickInitiator.addListener(tickListener);
    }
}
