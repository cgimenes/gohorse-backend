package com.xgh.model.command.operational.enumerator;

import com.xgh.buildingblocks.entity.EntityId;
import java.util.UUID;

public class EnumeratorId extends EntityId {
    public EnumeratorId() {
        super();
    }

    public EnumeratorId(String value) {
        super(value);
    }

    public EnumeratorId(UUID value) {
        super(value);
    }
}
