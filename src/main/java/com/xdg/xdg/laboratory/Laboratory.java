package com.xdg.xdg.laboratory;

import com.xdg.buildingblocks.Entity;
import com.xdg.valueobjects.Name;
import com.xdg.valueobjects.Phone;

public class Laboratory extends Entity<LaboratoryId>{

    private Name name;
    private Phone phone;

    public Laboratory(LaboratoryId id, Name name, Phone phone) {
        super(id);
        this.name = name;
        this.phone = phone;
    }
}
