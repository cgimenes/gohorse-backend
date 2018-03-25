package com.xgh.valueobjects;

import java.util.UUID;

import javax.persistence.MappedSuperclass;

import com.xgh.buildingblocks.SingleValueObject;

@MappedSuperclass
public abstract class EntityId extends SingleValueObject<UUID> {
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
