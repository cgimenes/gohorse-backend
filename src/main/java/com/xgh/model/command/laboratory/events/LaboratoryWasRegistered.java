package com.xgh.model.command.laboratory.events;

import com.xgh.buildingblocks.event.Event;
import com.xgh.model.command.laboratory.LaboratoryId;
import com.xgh.model.command.valueobjects.Address;
import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.model.command.valueobjects.Name;
import com.xgh.model.command.valueobjects.Phone;

public class LaboratoryWasRegistered extends Event<LaboratoryId> {
    private Name companyName;
    private Phone phone;
    private Address address;

    protected LaboratoryWasRegistered() {}

    public LaboratoryWasRegistered(LaboratoryId id, Name companyName, Phone phone, Address address, EntityVersion version) {
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
