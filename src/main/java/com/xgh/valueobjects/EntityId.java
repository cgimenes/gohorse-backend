package com.xgh.valueobjects;

import java.util.UUID;

import com.xgh.buildingblocks.SingleValueObject;

public abstract class EntityId extends SingleValueObject<UUID> {
	private static final long serialVersionUID = 3079215611563351873L;

	public EntityId(UUID id) {
        super(id);
    }
    
    public EntityId(String id) {
    	this(UUID.fromString(id));
    }
    
    public EntityId() {
        this(UUID.randomUUID());
    }
}
