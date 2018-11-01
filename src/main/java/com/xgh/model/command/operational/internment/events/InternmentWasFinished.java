package com.xgh.model.command.operational.internment.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.model.command.operational.internment.InternmentId;

public class InternmentWasFinished extends EntityEvent<InternmentId> {
    protected InternmentWasFinished() {
    }

    public InternmentWasFinished(InternmentId id, EntityVersion version) {
        super(id, version);
    }
}
