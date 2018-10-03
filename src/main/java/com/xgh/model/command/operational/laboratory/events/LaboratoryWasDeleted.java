package com.xgh.model.command.operational.laboratory.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.model.command.operational.laboratory.LaboratoryId;

public class LaboratoryWasDeleted extends EntityEvent<LaboratoryId> {
    protected LaboratoryWasDeleted() {
    }

    public LaboratoryWasDeleted(LaboratoryId id, EntityVersion version) {
        super(id, version);
    }
}
