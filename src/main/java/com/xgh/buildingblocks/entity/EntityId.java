package com.xgh.buildingblocks.entity;

import java.util.UUID;

import com.xgh.buildingblocks.valueobject.SingleValueObject;

public abstract class EntityId extends SingleValueObject<UUID> {
    protected EntityId(UUID id) {
        super(id);
    }
    
    protected EntityId(String id) {
        this(UUID.fromString(id));
    }
    
    protected EntityId() {
        this(UUID.randomUUID());
    }
}
