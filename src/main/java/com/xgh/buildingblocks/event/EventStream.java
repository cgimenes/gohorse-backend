package com.xgh.buildingblocks.event;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EventStream implements Iterator<EntityEvent<?>> {
    private final List<EntityEvent<?>> events;

    public EventStream() {
        this.events = new ArrayList<>();
    }

    public EventStream(List<EntityEvent<?>> events) {
        this.events = events;
    }

    @Override
    public boolean hasNext() {
        return !events.isEmpty();
    }

    @Override
    public EntityEvent<?> next() {
        return events.remove(0);
    }

    public void add(EntityEvent<?> event) {
        this.events.add(event);
    }
}
