package com.xgh.model.command.operational.laboratory;

import com.xgh.buildingblocks.entity.EntityId;
import java.util.UUID;

// TODO: remover construtores desnecessários dos ids
public class LaboratoryId extends EntityId {
    public LaboratoryId(String value) {
        super(value);
    }

    public LaboratoryId(UUID value) {
        super(value);
    }

    public LaboratoryId() {
        super();
    }
}
