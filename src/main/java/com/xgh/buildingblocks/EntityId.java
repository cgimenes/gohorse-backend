package com.xgh.buildingblocks;

import java.util.UUID;

public class EntityId extends SingleValueObject<UUID> {
	public EntityId(UUID id) {
        super(id);
    }
    
    public EntityId(String id) {
    	this(UUID.fromString(id));
    }
}
