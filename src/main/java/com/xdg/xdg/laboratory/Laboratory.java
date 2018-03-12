package com.xdg.xdg.laboratory;

import com.xdg.buildingblocks.Entity;
import com.xdg.buildingblocks.EventBus;
import com.xdg.valueobjects.Name;
import com.xdg.valueobjects.Phone;
import com.xdg.xdg.laboratory.events.LaboratoryWasRegistered;

public final class Laboratory extends Entity<LaboratoryId> {
    private Name name;
    private Phone phone;

    public Laboratory(LaboratoryId id, Name name, Phone phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public Laboratory() {
    }

    public static Laboratory register(LaboratoryId id, Name name, Phone phone) {
        Laboratory instance = new Laboratory();
        instance.id = id;
        instance.name = name;
        instance.phone = phone;

        EventBus.dispatch(new LaboratoryWasRegistered(id));
        return instance;
    }
}
