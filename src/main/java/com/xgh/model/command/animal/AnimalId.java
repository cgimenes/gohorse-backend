package com.xgh.model.command.animal;

import java.util.UUID;

import com.xgh.buildingblocks.entity.EntityId;

public class AnimalId extends EntityId{
	private static final long serialVersionUID = 6232106678804834785L;

	public AnimalId() {
		super();
	}
	
	public AnimalId(String value) {
		super(value);
	}
	
	public AnimalId(UUID value) {
		super(value);
	}
}

