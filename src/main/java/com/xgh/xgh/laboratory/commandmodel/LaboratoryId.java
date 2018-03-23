package com.xgh.xgh.laboratory.commandmodel;

import java.util.UUID;

import com.xgh.valueobjects.EntityId;

public class LaboratoryId extends EntityId {
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
