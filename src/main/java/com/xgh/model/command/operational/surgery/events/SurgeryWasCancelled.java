package com.xgh.model.command.operational.surgery.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.model.command.operational.surgery.SurgeryId;

public class SurgeryWasCancelled extends EntityEvent<SurgeryId> {
	protected SurgeryWasCancelled() {
	}
	
	public SurgeryWasCancelled(SurgeryId id, EntityVersion version) {
		super(id, version);
	}
}
