package com.xgh.model.command.operational.surgery;

import java.util.UUID;

import com.xgh.buildingblocks.entity.EntityId;

public class SurgeryId extends EntityId{
	public SurgeryId(String value) {
		super(value);
	}
	
	public SurgeryId(UUID value) {
		super(value);
	}
	
	public SurgeryId() {
		super();
	}
}
