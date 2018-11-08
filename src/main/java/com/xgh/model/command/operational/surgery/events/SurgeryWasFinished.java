package com.xgh.model.command.operational.surgery.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.model.command.operational.surgery.SurgeryId;

public class SurgeryWasFinished extends EntityEvent<SurgeryId> {
	protected SurgeryWasFinished() {
	}
	
	public SurgeryWasFinished(SurgeryId id, EntityVersion version) {
		super(id, version);
	}
}
