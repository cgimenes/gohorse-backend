package com.xgh.model.command.veterinary;

import com.xgh.buildingblocks.entity.EntityId;
import java.util.UUID;

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
