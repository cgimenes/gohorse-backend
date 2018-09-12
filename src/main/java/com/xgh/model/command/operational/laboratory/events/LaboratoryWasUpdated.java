package com.xgh.model.command.operational.laboratory.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.Event;
import com.xgh.model.command.operational.laboratory.LaboratoryId;
import com.xgh.model.command.operational.valueobjects.Address;
import com.xgh.model.command.operational.valueobjects.Name;
import com.xgh.model.command.operational.valueobjects.Phone;

public class LaboratoryWasUpdated extends Event<LaboratoryId> {
    private Name companyName;
    private Phone phone;
    private Address address;

    protected LaboratoryWasUpdated() {
    }

    public LaboratoryWasUpdated(LaboratoryId id, Name companyName, Phone phone, Address address, EntityVersion version) {
        super(id, version);
        this.companyName = companyName;
        this.phone = phone;
        this.address = address;
    }

    public Name getCompanyName() {
        return this.companyName;
    }

    public Phone getPhone() {
        return this.phone;
    }

    public Address getAddress() {
        return this.address;
    }
}
