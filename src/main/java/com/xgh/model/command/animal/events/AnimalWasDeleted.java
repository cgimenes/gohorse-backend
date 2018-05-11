package com.xgh.model.command.animal.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.Event;
import com.xgh.model.command.animal.AnimalId;

public class AnimalWasDeleted extends Event<AnimalId>{
	private static final long serialVersionUID = -7778110960386715367L;

	protected AnimalWasDeleted() {}
	
	public AnimalWasDeleted(AnimalId id, EntityVersion version) {
		super(id, version);
	}
}
