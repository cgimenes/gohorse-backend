package com.xgh.model.command.operational.veterinary.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.model.command.operational.veterinary.VeterinaryId;

public class VeterinaryWasDeleted extends EntityEvent<VeterinaryId> {
    protected VeterinaryWasDeleted() {
    }

    public VeterinaryWasDeleted(VeterinaryId id, EntityVersion version) {
        super(id, version);
    }
}
