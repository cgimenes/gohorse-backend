package com.xgh.model.command.operational.bed.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.model.command.operational.bed.BedId;

public class BedWasDeleted extends EntityEvent<BedId> {
    protected BedWasDeleted() {
    }

    public BedWasDeleted(BedId id, EntityVersion version) {
        super(id, version);
    }
}
