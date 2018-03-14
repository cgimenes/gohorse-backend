package com.xgh.xgh.command.laboratory.commands;

import com.xgh.buildingblocks.Command;
import com.xgh.valueobjects.Name;
import com.xgh.valueobjects.Phone;
import com.xgh.xgh.command.laboratory.LaboratoryId;

public final class RegisterLaboratory extends Command {
    private final LaboratoryId id;
    private final Name name;
    private final Phone phone;

    public RegisterLaboratory() {
        // Gera um id caso o cliente não tenha passado na API
        this.id = new LaboratoryId();
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
