package com.xgh.model.command.operational.enumerators.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.model.command.operational.enumerators.EnumeratorId;

public class EnumeratorWasDeleted extends EntityEvent<EnumeratorId> {
    protected EnumeratorWasDeleted() {
    }

    public EnumeratorWasDeleted(EnumeratorId id, EntityVersion version) {
        super(id, version);
    }
}
