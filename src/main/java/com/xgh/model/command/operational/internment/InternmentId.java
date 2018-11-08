package com.xgh.model.command.operational.internment;

import com.xgh.buildingblocks.entity.EntityId;
import com.xgh.model.command.operational.valueobjects.OperationId;
import java.util.UUID;

public class InternmentId extends OperationId {
    public InternmentId(UUID value) {
        super(value);
    }

    public InternmentId(String value) {
        super(value);
    }

    public InternmentId() {
        super();
    }
}
