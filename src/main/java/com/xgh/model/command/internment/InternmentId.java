package com.xgh.model.command.internment;

import com.xgh.buildingblocks.entity.EntityId;
import java.util.UUID;

public class InternmentId extends EntityId {
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
