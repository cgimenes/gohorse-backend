package com.xgh.model.command.bed;

import java.util.UUID;

import com.xgh.buildingblocks.entity.EntityId;

public class BedId extends EntityId{
    public BedId(String value) {
        super(value);
    }

    public BedId(UUID value) {
        super(value);
    }

    public BedId() {
        super();
    }
}
