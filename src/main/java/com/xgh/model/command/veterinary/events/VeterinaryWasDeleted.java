package com.xgh.model.command.veterinary.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.Event;
import com.xgh.model.command.veterinary.VeterinaryId;

public class VeterinaryWasDeleted extends Event<VeterinaryId> {
    protected VeterinaryWasDeleted() {
    }

    public VeterinaryWasDeleted(VeterinaryId id, EntityVersion version) {
        super(id, version);
    }
}
