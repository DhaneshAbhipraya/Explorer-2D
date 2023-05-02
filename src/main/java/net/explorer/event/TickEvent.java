package net.explorer.event;

import java.util.ArrayList;
import java.util.List;

public class TickEvent {
    public interface TickListener {
        void onStartTick();
        void onEndTick();
    }

    public static class TickInitiator {
        private final List<TickListener> listeners = new ArrayList<>();

        public void addListener(TickListener toAdd) {
            listeners.add(toAdd);
        }

        public void startTick() {
            for (TickListener tL : listeners) tL.onStartTick();
        }

        public void endTick() {
            for (TickListener tL : listeners) tL.onEndTick();
        }
    }
}