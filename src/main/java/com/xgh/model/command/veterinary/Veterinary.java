package com.xgh.model.command.veterinary;

import com.xgh.buildingblocks.entity.AggregateRoot;
import com.xgh.exceptions.NullMandatoryArgumentException;
import com.xgh.model.command.valueobjects.Address;
import com.xgh.model.command.valueobjects.Crmv;
import com.xgh.model.command.valueobjects.Email;
import com.xgh.model.command.valueobjects.Name;
import com.xgh.model.command.valueobjects.Phone;
import com.xgh.model.command.veterinary.events.VeterinaryWasDeleted;
import com.xgh.model.command.veterinary.events.VeterinaryWasRegistered;
import com.xgh.model.command.veterinary.events.VeterinaryWasUpdated;
import java.time.LocalDate;

public class Veterinary extends AggregateRoot<VeterinaryId> {
    private Name name;
    private Address address;
    private Phone phone;
    private Crmv crmv;
    private Email email;
    private LocalDate birthDate;

    public void register(VeterinaryId id, Name name, Address address, Phone phone, Crmv crmv, Email email, LocalDate birthDate) {
        if (id == null) {
            throw new NullMandatoryArgumentException("id");
        }

        if (name == null) {
            throw new NullMandatoryArgumentException("nome");
        }

        if (address == null) {
            throw new NullMandatoryArgumentException("endereço");
        }

        if (phone == null) {
            throw new NullMandatoryArgumentException("telefone");
        }

        if (crmv == null) {
            throw new NullMandatoryArgumentException("CRMV");
        }

        if (email == null) {
            throw new NullMandatoryArgumentException("e-mail");
        }

        if (birthDate == null) {
            throw new NullMandatoryArgumentException("data de nascimento");
        }

        recordAndApply(new VeterinaryWasRegistered(id, name, address, phone, crmv, email, birthDate, this.nextVersion()));
    }

    public void update(Name name, Address address, Phone phone, Crmv crmv, Email email, LocalDate birthDate) {
        recordAndApply(new VeterinaryWasUpdated(this.id, name, address, phone, crmv, email, birthDate, this.nextVersion()));
    }

    public void delete() {
        recordAndApply(new VeterinaryWasDeleted(this.id, this.nextVersion()));
    }

    protected void when(VeterinaryWasRegistered event) {
        this.name = event.getName();
        this.address = event.getAddress();
        this.phone = event.getPhone();
        this.crmv = event.getCrmv();
        this.email = event.getEmail();
        this.birthDate = event.getBirthDate();
    }

    protected void when(VeterinaryWasUpdated event) {
        this.name = event.getName();
        this.address = event.getAddress();
        this.phone = event.getPhone();
        this.crmv = event.getCrmv();
        this.email = event.getEmail();
        this.birthDate = event.getBirthDate();
    }

    protected void when(VeterinaryWasDeleted event) {
        this.markDeleted();
    }

    public Name getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public Phone getPhone() {
        return phone;
    }

    public Crmv getCrmv() {
        return crmv;
    }

    public Email getEmail() {
        return email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
}
