package com.xgh.model.command.additionalregister;

import com.xgh.buildingblocks.entity.EntityId;
import java.util.UUID;

public class AdditionalRegisterId extends EntityId {
    public AdditionalRegisterId() {
        super();
    }

    public AdditionalRegisterId(String value) {
        super(value);
    }

    public AdditionalRegisterId(UUID value) {
        super(value);
    }
}
