package com.xgh.model.command.veterinary;

import java.util.UUID;

import com.xgh.buildingblocks.entity.EntityId;

public class VeterinaryId extends EntityId {
    public VeterinaryId() {
        super();
    }

    public VeterinaryId(String value) {
        super(value);
    }

    public VeterinaryId(UUID value) {
        super(value);
    }
}
