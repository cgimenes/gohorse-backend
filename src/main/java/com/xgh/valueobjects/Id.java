package com.xgh.valueobjects;

import java.util.UUID;

import com.xgh.buildingblocks.SingleValueObject;

public class Id extends SingleValueObject<UUID> {
	public Id(UUID id) {
        super(id);
    }
    
    public Id(String id) {
    	this(UUID.fromString(id));
    }
}
