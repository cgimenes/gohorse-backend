package com.xgh.model.command.operational.owner.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.model.command.operational.owner.OwnerId;
import com.xgh.model.command.operational.valueobjects.Address;
import com.xgh.model.command.operational.valueobjects.Document;
import com.xgh.model.command.operational.valueobjects.Email;
import com.xgh.model.command.operational.valueobjects.Name;
import com.xgh.model.command.operational.valueobjects.Phone;
import java.time.LocalDate;

public class OwnerWasRegistered extends EntityEvent<OwnerId> {
    private Name name;
    private Phone phone;
    private Document document;
    private LocalDate birthDate;
    private Address address;
    private Email email;

    protected OwnerWasRegistered() {
    }

    public OwnerWasRegistered(OwnerId id, Name name, Phone phone, Document document, LocalDate birthDate, Address address, Email email, EntityVersion version) {
        super(id, version);
        this.name = name;
        this.phone = phone;
        this.document = document;
        this.birthDate = birthDate;
        this.address = address;
        this.email = email;
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
    
    public Email getEmail() {
		return email;
	}

}
