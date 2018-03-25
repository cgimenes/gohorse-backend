package com.xgh.xgh.laboratory.commandmodel;

import java.util.UUID;

import com.xgh.valueobjects.EntityId;

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
