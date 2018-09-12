package com.xgh.model.command.operational.veterinary.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.operational.valueobjects.Address;
import com.xgh.model.command.operational.valueobjects.Crmv;
import com.xgh.model.command.operational.valueobjects.Email;
import com.xgh.model.command.operational.valueobjects.Name;
import com.xgh.model.command.operational.valueobjects.Phone;
import com.xgh.model.command.operational.veterinary.VeterinaryId;
import java.time.LocalDate;

public class UpdateVeterinary implements Command {
    private final VeterinaryId id;
    private final Name name;
    private final Address address;
    private final Phone phone;
    private final Crmv crmv;
    private final Email email;
    private final LocalDate birthDate;

    protected UpdateVeterinary() {
        this.id = null;
        this.name = null;
        this.address = null;
        this.phone = null;
        this.crmv = null;
        this.email = null;
        this.birthDate = null;
    }

    @Override
    public VeterinaryId getId() {
        return this.id;
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