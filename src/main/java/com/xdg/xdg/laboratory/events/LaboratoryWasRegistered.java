package com.xdg.xdg.laboratory.events;

import com.xdg.buildingblocks.Event;
import com.xdg.valueobjects.Name;
import com.xdg.valueobjects.Phone;
import com.xdg.xdg.laboratory.LaboratoryId;

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
