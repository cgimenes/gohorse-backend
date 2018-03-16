package com.xgh.xgh.command.laboratory;

import java.util.UUID;

import com.xgh.valueobjects.Id;

public class LaboratoryId extends Id {
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
