package com.xdg.xdg.laboratory.commands;

import com.xdg.buildingblocks.Command;
import com.xdg.valueobjects.Name;
import com.xdg.valueobjects.Phone;
import com.xdg.xdg.laboratory.LaboratoryId;

public final class RegisterLaboratory extends Command {
    private final LaboratoryId id;
    private final Name name;
    private final Phone phone;

    public RegisterLaboratory(LaboratoryId id, Name name, Phone phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public RegisterLaboratory(Name name, Phone phone) {
        this.id = null;
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
