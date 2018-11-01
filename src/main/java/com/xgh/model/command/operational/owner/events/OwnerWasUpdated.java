package com.xgh.model.command.operational.owner.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.model.command.operational.owner.OwnerId;
import com.xgh.model.command.operational.valueobjects.Address;
import com.xgh.model.command.operational.valueobjects.Document;
import com.xgh.model.command.operational.valueobjects.Name;
import com.xgh.model.command.operational.valueobjects.Phone;
import java.time.LocalDate;

public class OwnerWasUpdated extends EntityEvent<OwnerId> {
    private Name name;
    private Phone phone;
    private Document document;
    private LocalDate birthDate;
    private Address address;

    protected OwnerWasUpdated() {
    }

    public OwnerWasUpdated(OwnerId id, Name name, Phone phone, Document document, LocalDate birthDate, Address address, EntityVersion version) {
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

    public Document getDocument() {
        return document;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Address getAddress() {
        return address;
    }
}
