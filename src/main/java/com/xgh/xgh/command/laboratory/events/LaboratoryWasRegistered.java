package com.xgh.xgh.command.laboratory.events;

import com.xgh.buildingblocks.Event;
import com.xgh.xgh.command.laboratory.LaboratoryId;

public class LaboratoryWasRegistered extends Event {
    private final LaboratoryId id;

    public LaboratoryWasRegistered(LaboratoryId id) {
        super();
        this.id = id;
    }

    public LaboratoryId getId() {
        return id;
    }
}
