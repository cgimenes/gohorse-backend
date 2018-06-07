package com.xgh.model.command.owner;

import com.xgh.buildingblocks.entity.EntityId;
import java.util.UUID;

public class OwnerId extends EntityId {
    public OwnerId() {
        super();
    }

    public OwnerId(String value) {
        super(value);
    }

    public OwnerId(UUID value) {
        super(value);
    }
}
