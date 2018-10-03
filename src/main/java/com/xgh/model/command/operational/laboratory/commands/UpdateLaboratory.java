package com.xgh.model.command.operational.laboratory.commands;

import com.xgh.buildingblocks.command.EntityCommand;
import com.xgh.model.command.operational.laboratory.LaboratoryId;
import com.xgh.model.command.operational.valueobjects.Address;
import com.xgh.model.command.operational.valueobjects.Name;
import com.xgh.model.command.operational.valueobjects.Phone;

public final class UpdateLaboratory implements EntityCommand {
    private final LaboratoryId id;
    private final Name companyName;
    private final Phone phone;
    private final Address address;

    protected UpdateLaboratory() {
        this.id = null;
        this.companyName = null;
        this.phone = null;
        this.address = null;
    }

    @Override
    public LaboratoryId getId() {
        return id;
    }

    public Name getCompanyName() {
        return companyName;
    }

    public Phone getPhone() {
        return phone;
    }

    public Address getAddress() {
        return address;
    }
}
