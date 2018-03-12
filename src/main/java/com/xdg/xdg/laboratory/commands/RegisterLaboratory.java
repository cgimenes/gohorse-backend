package com.xdg.xdg.laboratory.commands;

import com.xdg.buildingblocks.Command;
import com.xdg.valueobjects.Name;
import com.xdg.valueobjects.Phone;
import com.xdg.xdg.laboratory.LaboratoryId;

public final class RegisterLaboratory extends Command {
    private final LaboratoryId id;
    private final Name name;
    private final Phone phone;

    public RegisterLaboratory() {
        // Gera um id caso o cliente não tenha passado na API
        this.id = LaboratoryId.generate();
        this.name = null;
        this.phone = null;
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
