package com.xgh.model.command.operational.valueobjects;

import com.xgh.buildingblocks.entity.EntityId;
import java.util.UUID;

public class OperationId extends EntityId {
    public OperationId(UUID id) {
        super(id);
    }

    public OperationId(String id) {
        super(id);
    }

    public OperationId() {
        super();
    }
}
