package com.xgh.xgh.laboratory.command;

import java.util.UUID;

import com.xgh.buildingblocks.EntityId;

public class LaboratoryId extends EntityId {
    public LaboratoryId(String value) {
		super(value);
	}
    
    public LaboratoryId(UUID value) {
		super(value);
	}
    
    // TODO: passar para super classe
    public static LaboratoryId generate() {
        return new LaboratoryId(UUID.randomUUID());
    }
}
