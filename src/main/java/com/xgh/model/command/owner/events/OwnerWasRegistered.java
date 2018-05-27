package com.xgh.model.command.owner.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.Event;
import com.xgh.model.command.owner.OwnerId;
import com.xgh.model.command.valueobjects.*;

public class OwnerWasRegistered extends Event<OwnerId> {
    private Name name;
    private Phone phone;
    private Cpf cpf;
    private Date birthDate;
    private Address address;

    protected OwnerWasRegistered() {
    }

    public OwnerWasRegistered(OwnerId id, Name name, Phone phone, Cpf cpf, Date birthDate, Address address, EntityVersion version) {
        super(id, version);
        this.name = name;
        this.phone = phone;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.address = address;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Address getAddress() {
        return address;
    }
}
