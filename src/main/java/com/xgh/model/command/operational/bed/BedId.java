package com.xgh.model.command.operational.bed;

import com.xgh.buildingblocks.entity.EntityId;
import java.util.UUID;

public class BedId extends EntityId {
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
