package com.xgh.xgh.command.laboratory.events;

import com.xgh.buildingblocks.Event;
import com.xgh.xgh.command.laboratory.LaboratoryId;

public class LaboratoryWasUpdated extends Event {
    private final LaboratoryId id;

    public LaboratoryWasUpdated(LaboratoryId id) {
        super();
        this.id = id;
    }

    public LaboratoryId getId() {
        return id;
    }
}
