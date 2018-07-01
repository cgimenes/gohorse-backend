package com.xgh.model.command.supplier;

import com.xgh.buildingblocks.entity.EntityId;
import java.util.UUID;

public class SupplierId extends EntityId {
    public SupplierId() {
        super();
    }

    public SupplierId(String value) {
        super(value);
    }

    public SupplierId(UUID value) {
        super(value);
    }
}
