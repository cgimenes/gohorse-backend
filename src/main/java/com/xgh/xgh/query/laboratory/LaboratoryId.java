package com.xgh.xgh.query.laboratory;

import java.util.UUID;

import com.xgh.valueobjects.Id;

public class LaboratoryId extends Id {
    public LaboratoryId(String value) {
		super(value);
	}
    
    public LaboratoryId(UUID value) {
		super(value);
	}
}
