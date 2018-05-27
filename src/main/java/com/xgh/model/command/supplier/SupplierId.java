package com.xgh.model.command.supplier;

import com.xgh.buildingblocks.entity.EntityId;
import java.util.UUID;

public class SupplierId extends EntityId {
    public SupplierId(UUID id) {
        super(id);
    }

    public SupplierId(String id) {
        super(id);
    }

    public SupplierId() {
        super();
    }
}
