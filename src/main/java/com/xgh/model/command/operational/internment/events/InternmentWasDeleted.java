package com.xgh.model.command.operational.internment.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.model.command.operational.internment.InternmentId;

public class InternmentWasDeleted extends EntityEvent<InternmentId> {
    protected InternmentWasDeleted() {}

    public InternmentWasDeleted(InternmentId id, EntityVersion version) {
        super(id, version);
    }
}
