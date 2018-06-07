package com.xgh.model.command.owner.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.owner.OwnerId;
import com.xgh.model.command.valueobjects.Address;
import com.xgh.model.command.valueobjects.Cpf;
import com.xgh.model.command.valueobjects.Date;
import com.xgh.model.command.valueobjects.Name;
import com.xgh.model.command.valueobjects.Phone;

public final class RegisterOwner implements Command {
    private final OwnerId id;
    private final Name name;
    private final Document document;
    private final Phone phone;
    private final Date birthDate;
    private final Address address;

    protected RegisterOwner() {
        this.id = new OwnerId();
        this.name = null;
        this.document = null;
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

    public Document getDocument() {
        return this.document;
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
