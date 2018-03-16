package com.xgh.xgh.command.laboratory.commands;

import com.xgh.buildingblocks.Command;
import com.xgh.valueobjects.Name;
import com.xgh.valueobjects.Phone;
import com.xgh.xgh.command.laboratory.LaboratoryId;

public final class RegisterLaboratory extends Command {
    private final LaboratoryId id;
    private final Name companyName;
    private final Phone phone;

    public RegisterLaboratory() {
        // Gera um id caso o cliente não tenha passado na API
        this.id = LaboratoryId.generate();
        this.companyName = null;
        this.phone = null;
    }

    public LaboratoryId getId() {
        return id;
    }

    public Name getCompanyName() {
        return companyName;
    }

    public Phone getPhone() {
        return phone;
    }
}
