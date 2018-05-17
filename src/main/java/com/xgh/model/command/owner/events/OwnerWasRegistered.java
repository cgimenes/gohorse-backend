package com.xgh.model.command.owner.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.Event;
import com.xgh.model.command.owner.OwnerId;
import com.xgh.model.command.valueobjects.*;

public class OwnerWasRegistered extends Event<OwnerId> {
    private static final long serialVersionUID = -3062625947435023015L;

    private Name name;
    private Phone phone;
    private String document;
    private Date birthDate;
    private Address address;

    protected OwnerWasRegistered() {}

    public OwnerWasRegistered(OwnerId id, Name name, Phone phone, String document, Date birthDate, Address address, EntityVersion version) {
        super(id, version);
        this.name = name;
        this.phone = phone;
        this.document = document;
        this.birthDate = birthDate;
        this.address = address;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public String getDocument() {
        return document;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Address getAddress() {
        return address;
    }
}
