package com.xdg.xdg.laboratory;

import com.xdg.buildingblocks.Entity;
import com.xdg.valueobjects.Name;
import com.xdg.valueobjects.Phone;
import com.xdg.xdg.laboratory.events.LaboratoryWasRegistered;

public final class Laboratory extends Entity<LaboratoryId>{

    private Name name;
    private Phone phone;

    public Laboratory() {
    }

    public void register(LaboratoryId id, Name name, Phone phone) {

    }

    public void apply(LaboratoryWasRegistered event) {
        this.id = event.getId();
        this.name = event.getName();
        this.phone = event.getPhone();
    }
}
