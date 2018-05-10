package com.xgh.model.command.bed.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.Event;
import com.xgh.model.command.bed.BedId;

public class BedWasDeleted extends Event<BedId> {
	protected BedWasDeleted() {}

	public BedWasDeleted(BedId id, EntityVersion version) {
		super(id, version);
	}
}
