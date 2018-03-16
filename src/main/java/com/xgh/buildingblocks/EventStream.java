package com.xgh.buildingblocks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EventStream implements Iterator<Event<?>> {
	private List<Event<?>> events;

	public EventStream() {
		this.events = new ArrayList<>();
	}
	
	private EventStream(List<Event<?>> events) {
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
	
	public EventStream add(Event<?> event) {
		List<Event<?>> newList = new ArrayList<>(this.events);
		newList.add(event);
		return new EventStream(newList);		
	}
}
