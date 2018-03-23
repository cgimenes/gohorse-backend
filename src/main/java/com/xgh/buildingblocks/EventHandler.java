package com.xgh.buildingblocks;

public interface EventHandler {
    boolean isSubscribedTo(Event<?> event);

    void execute(Event<?> event);
}
