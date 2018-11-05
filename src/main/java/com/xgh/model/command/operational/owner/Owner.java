package com.xgh.model.command.operational.owner;

import com.xgh.buildingblocks.entity.AggregateRoot;
import com.xgh.exceptions.NullMandatoryArgumentException;
import com.xgh.model.command.operational.owner.events.OwnerWasDeleted;
import com.xgh.model.command.operational.owner.events.OwnerWasRegistered;
import com.xgh.model.command.operational.owner.events.OwnerWasUpdated;
import com.xgh.model.command.operational.valueobjects.Address;
import com.xgh.model.command.operational.valueobjects.Document;
import com.xgh.model.command.operational.valueobjects.Email;
import com.xgh.model.command.operational.valueobjects.Name;
import com.xgh.model.command.operational.valueobjects.Phone;
import java.time.LocalDate;

public final class Owner extends AggregateRoot<OwnerId> {
    private Name name;
    private Phone phone;
    private Document document;
    private LocalDate birthDate;
    private Address address;
    private Email email;

    public Owner() {
        super();
    }

    public void register(OwnerId id, Name name, Phone phone, Document document, LocalDate birthDate, Address address, Email email) {
        if (id == null) {
            throw new NullMandatoryArgumentException("id");
        }

        if (name == null) {
            throw new NullMandatoryArgumentException("nome");
        }

        if (phone == null) {
            throw new NullMandatoryArgumentException("telefone");
        }

        if (document == null) {
            throw new NullMandatoryArgumentException("CPF");
        }

        if (birthDate == null) {
            throw new NullMandatoryArgumentException("data de nascimento");
        }

        if (address == null) {
            throw new NullMandatoryArgumentException("endereço");
        }

        recordAndApply(new OwnerWasRegistered(id, name, phone, document, birthDate, address, email, this.nextVersion()));
    }

    public void update(Name name, Phone phone, Document document, LocalDate birthDate, Address address, Email email) {
        recordAndApply(new OwnerWasUpdated(this.id, name, phone, document, birthDate, address, email, this.nextVersion()));
    }

    public void delete() {
        recordAndApply(new OwnerWasDeleted(this.id, this.nextVersion()));
    }

    protected void when(OwnerWasRegistered event) {
        this.id = event.getEntityId();
        this.name = event.getName();
        this.document = event.getDocument();
        this.phone = event.getPhone();
        this.birthDate = event.getBirthDate();
        this.address = event.getAddress();
        this.email = event.getEmail();
    }

    protected void when(OwnerWasUpdated event) {
        this.name = event.getName();
        this.document = event.getDocument();
        this.phone = event.getPhone();
        this.birthDate = event.getBirthDate();
        this.address = event.getAddress();
        this.email = event.getEmail();
    }

    protected void when(OwnerWasDeleted event) {
        this.markDeleted();
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

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public Address getAddress() {
        return this.address;
    }
    
    public Email getEmail() {
		return this.email;
	}

}
