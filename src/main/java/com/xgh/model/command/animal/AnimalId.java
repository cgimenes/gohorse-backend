package com.xgh.model.command.animal;

import com.xgh.buildingblocks.entity.EntityId;

import java.util.UUID;

public class AnimalId extends EntityId {
    public AnimalId() {
        super();
    }

    public AnimalId(String value) {
        super(value);
    }

    public AnimalId(UUID value) {
        super(value);
    }
}

