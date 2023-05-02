package net.explorer.event;

public class Events {
    private static Events instance;
    public TickEvent.TickInitiator tickInitiator;

    public static Events getInstance() {
        return instance;
    }

    public Events() {
        instance = this;
        this.tickInitiator = new TickEvent.TickInitiator();
    }
}
