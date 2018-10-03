package com.xgh.model.command.operational.animal.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.model.command.operational.animal.AnimalId;

public class AnimalWasDeleted extends EntityEvent<AnimalId> {
    protected AnimalWasDeleted() {
    }

    public AnimalWasDeleted(AnimalId id, EntityVersion version) {
        super(id, version);
    }
}
