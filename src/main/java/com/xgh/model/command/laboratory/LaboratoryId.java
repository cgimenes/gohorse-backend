package com.xgh.model.command.laboratory;

import java.util.UUID;

import com.xgh.buildingblocks.entity.EntityId;

public class LaboratoryId extends EntityId {
	private static final long serialVersionUID = -5011668817962402691L;

	public LaboratoryId(String value) {
		super(value);
	}
    
    public LaboratoryId(UUID value) {
		super(value);
	}
    
    public LaboratoryId() {
		super();
	}
}
