package com.xdg.xdg.laboratory.events;

import com.xdg.buildingblocks.Event;
import com.xdg.valueobjects.Name;
import com.xdg.valueobjects.Phone;
import com.xdg.xdg.laboratory.LaboratoryId;

public class LaboratoryWasRegistered extends Event {
    private final LaboratoryId id;
    private final Name name;
    private final Phone phone;

    public LaboratoryWasRegistered(LaboratoryId id, Name name, Phone phone) {
        super();
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public LaboratoryId getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }
}
