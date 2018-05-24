package com.xgh.model.command.animal.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.Event;
import com.xgh.model.command.animal.AnimalId;

public class AnimalWasDeleted extends Event<AnimalId> {
	protected AnimalWasDeleted() {}
	
	public AnimalWasDeleted(AnimalId id, EntityVersion version) {
		super(id, version);
	}
}
