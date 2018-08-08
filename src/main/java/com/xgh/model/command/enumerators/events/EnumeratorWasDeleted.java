package com.xgh.model.command.enumerators.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.Event;
import com.xgh.model.command.enumerators.EnumeratorId;

public class EnumeratorWasDeleted extends Event<EnumeratorId> {
    protected EnumeratorWasDeleted() {
    }

    public EnumeratorWasDeleted(EnumeratorId id, EntityVersion version) {
        super(id, version);
    }
}
