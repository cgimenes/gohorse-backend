package com.xgh.model.command.owner.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.owner.OwnerId;
import com.xgh.model.command.valueobjects.Address;
import com.xgh.model.command.valueobjects.Date;
import com.xgh.model.command.valueobjects.Document;
import com.xgh.model.command.valueobjects.Name;
import com.xgh.model.command.valueobjects.Phone;

public final class UpdateOwner implements Command {
    private final OwnerId id;
    private final Name name;
    private final Document document;
    private final Phone phone;
    private final Date birthDate;
    private final Address address;

    protected UpdateOwner() {
        this.id = null;
        this.name = null;
        this.document = null;
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

    public Document getDocument() {
        return document;
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
