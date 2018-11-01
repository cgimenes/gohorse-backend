package com.xgh.model.command.operational.owner.commands;

import com.xgh.buildingblocks.command.EntityCommand;
import com.xgh.model.command.operational.owner.OwnerId;
import com.xgh.model.command.operational.valueobjects.Address;
import com.xgh.model.command.operational.valueobjects.Document;
import com.xgh.model.command.operational.valueobjects.Name;
import com.xgh.model.command.operational.valueobjects.Phone;
import java.time.LocalDate;

public final class UpdateOwner implements EntityCommand {
    private final OwnerId id;
    private final Name name;
    private final Document document;
    private final Phone phone;
    private final LocalDate birthDate;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Address getAddress() {
        return this.address;
    }
}
