package com.xgh.model.command.owner.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.Event;
import com.xgh.model.command.owner.OwnerId;

public class OwnerWasDeleted extends Event<OwnerId>{
    protected OwnerWasDeleted() {}

    public OwnerWasDeleted(OwnerId id, EntityVersion version) {
        super(id,version);
    }
}
