package com.xgh.model.command.owner.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.owner.OwnerId;
import com.xgh.model.command.valueobjects.*;

public final class UpdateOwner implements Command {
    private static final long serialVersionUID = -5468719672345592356L;

    private final OwnerId id;
    private final Name name;
    private final Cpf cpf;
    private final Phone phone;
    private final Date birthDate;
    private final Address address;

    protected UpdateOwner() {
        this.id = null;
        this.name = null;
        this.cpf = null;
        this.phone = null;
        this.birthDate = null;
        this.address = null;
    }

    @Override
    public OwnerId getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public Phone getPhone() {
        return phone;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Address getAddress() {
        return this.address;
    }
}
