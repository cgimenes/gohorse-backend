package com.xgh.buildingblocks;

public abstract class EventHandler {
    public abstract boolean isSubscribedTo(Event event);

    public abstract void execute(Event event);
}
