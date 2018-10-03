package com.xgh.model.command.operational.owner.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.model.command.operational.owner.OwnerId;

public class OwnerWasDeleted extends EntityEvent<OwnerId> {
    protected OwnerWasDeleted() {
    }

    public OwnerWasDeleted(OwnerId id, EntityVersion version) {
        super(id, version);
    }
}
