package com.xgh.buildingblocks.event;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EventStream implements Iterator<Event<?>> {
    private final List<Event<?>> events;

    public EventStream() {
        this.events = new ArrayList<>();
    }

    public EventStream(List<Event<?>> events) {
        this.events = events;
    }

    @Override
    public boolean hasNext() {
        return !events.isEmpty();
    }

    @Override
    public Event<?> next() {
        return events.remove(0);
    }

    public void add(Event<?> event) {
        this.events.add(event);
    }
}
