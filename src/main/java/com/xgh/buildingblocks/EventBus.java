package com.xgh.buildingblocks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EventBus {
    private List<EventHandler> handlers = new ArrayList<>();

    private static EventBus instance;

    private EventBus() {
    }

    private static EventBus getInstance() {
        if (instance == null) {
            instance = new EventBus();
        }
        return instance;
    }

    public static void addHandler(EventHandler eventHandler) {
        getInstance().handlers.add(eventHandler);
    }

    public static void addAllHandlers(Collection<? extends EventHandler> eventHandlers) {
        getInstance().handlers.addAll(eventHandlers);
    }

    public static void dispatch(Event<?> event) {
        for (EventHandler handler : getInstance().handlers) {
            if (handler.isSubscribedTo(event)) {
                handler.execute(event);
            }
        }
    }
}
