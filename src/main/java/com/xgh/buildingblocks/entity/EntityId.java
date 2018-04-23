package com.xgh.buildingblocks.entity;

import java.util.UUID;

import com.xgh.buildingblocks.valueobject.SingleValueObject;

public abstract class EntityId extends SingleValueObject<UUID> {
    private static final long serialVersionUID = 3079215611563351873L;

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
