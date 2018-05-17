package com.xgh.model.command.laboratory;

import java.util.UUID;

import com.xgh.buildingblocks.entity.EntityId;

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
