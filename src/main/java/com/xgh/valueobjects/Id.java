package com.xgh.valueobjects;

import java.util.UUID;

import com.xgh.buildingblocks.SingleValueObject;

abstract public class Id extends SingleValueObject<UUID> {
    public Id(UUID value) {
        super(value);
    }
    
    public Id(String value) {
    	this(UUID.fromString(value));
    }
    
    public Id() {
        super(UUID.randomUUID());
    }
}
