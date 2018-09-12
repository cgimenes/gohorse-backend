package com.xgh.model.command.operational.internment.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.Event;
import com.xgh.model.command.operational.internment.InternmentId;

public class InternmentWasDeleted extends Event<InternmentId> {
    protected InternmentWasDeleted() {}

    public InternmentWasDeleted(InternmentId id, EntityVersion version) {
        super(id, version);
    }
}
