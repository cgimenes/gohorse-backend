package com.xgh.model.command.operational.surgery.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.model.command.operational.surgery.SurgeryId;

public class SurgeryWasDeleted extends EntityEvent<SurgeryId> {
	protected SurgeryWasDeleted() {
	}
	
	public SurgeryWasDeleted(SurgeryId id, EntityVersion version) {
		super(id, version);
	}
}
