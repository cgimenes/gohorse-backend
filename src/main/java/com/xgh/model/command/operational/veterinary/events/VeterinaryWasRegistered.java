package com.xgh.model.command.operational.veterinary.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.model.command.operational.valueobjects.Address;
import com.xgh.model.command.operational.valueobjects.Crmv;
import com.xgh.model.command.operational.valueobjects.Email;
import com.xgh.model.command.operational.valueobjects.Name;
import com.xgh.model.command.operational.valueobjects.Phone;
import com.xgh.model.command.operational.veterinary.VeterinaryId;
import java.time.LocalDate;

public class VeterinaryWasRegistered extends EntityEvent<VeterinaryId> {
    private Name name;
    private Address address;
    private Phone phone;
    private Crmv crmv;
    private Email email;
    private LocalDate birthDate;

    protected VeterinaryWasRegistered() {
    }

    public VeterinaryWasRegistered(VeterinaryId id, Name name, Address address, Phone phone,
                                   Crmv crmv, Email email, LocalDate birthDate, EntityVersion version) {
        super(id, version);
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.crmv = crmv;
        this.email = email;
        this.birthDate = birthDate;
    }

    public Name getName() {
        return this.name;
    }

    public Address getAddress() {
        return this.address;
    }

    public Phone getPhone() {
        return this.phone;
    }

    public Crmv getCrmv() {
        return this.crmv;
    }

    public Email getEmail() {
        return this.email;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

}
