package com.xgh.model.command.owner.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.owner.OwnerId;
import com.xgh.model.command.valueobjects.*;

public final class RegisterOwner implements Command {
    private static final long serialVersionUID = 7829193234187879671L;

    private final OwnerId id;
    private final Name name;
    private final Cpf cpf;
    private final Phone phone;
    private final Date birthDate;
    private final Address address;

    protected RegisterOwner() {
        this.id = new OwnerId();
        this.name = null;
        this.cpf = null;
        this.phone = null;
        this.birthDate = null;
        this.address = null;
    }

    @Override
    public OwnerId getId() {
        return this.id;
    }

    public Name getName() {
        return this.name;
    }

    public Cpf getCpf() {
        return this.cpf;
    }

    public Phone getPhone() {
        return this.phone;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public Address getAddress() {
        return this.address;
    }
}
