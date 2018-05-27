package com.xgh.model.command.owner;

import com.xgh.buildingblocks.entity.AggregateRoot;
import com.xgh.exceptions.NullMandatoryArgumentException;
import com.xgh.model.command.owner.events.OwnerWasDeleted;
import com.xgh.model.command.owner.events.OwnerWasRegistered;
import com.xgh.model.command.owner.events.OwnerWasUpdated;
import com.xgh.model.command.valueobjects.Address;
import com.xgh.model.command.valueobjects.Cpf;
import com.xgh.model.command.valueobjects.Date;
import com.xgh.model.command.valueobjects.Name;
import com.xgh.model.command.valueobjects.Phone;

public final class Owner extends AggregateRoot<OwnerId> {
    private Name name;
    private Phone phone;
    private Cpf cpf;
    private Date birthDate;
    private Address address;

    public void register(OwnerId id, Name name, Phone phone, Cpf cpf, Date birthDate, Address address) {
        if (id == null) {
            throw new NullMandatoryArgumentException("id");
        }

        if (name == null) {
            throw new NullMandatoryArgumentException("nome");
        }

        if (phone == null) {
            throw new NullMandatoryArgumentException("telefone");
        }

        if (cpf == null) {
            throw new NullMandatoryArgumentException("CPF");
        }

        if (birthDate == null) {
            throw new NullMandatoryArgumentException("data de nascimento");
        }

        if (address == null) {
            throw new NullMandatoryArgumentException("endereço");
        }

        recordAndApply(new OwnerWasRegistered(id, name, phone, cpf, birthDate, address, this.nextVersion()));
    }

    public void update(Name name, Phone phone, Cpf cpf, Date birthDate, Address address) {
        recordAndApply(new OwnerWasUpdated(this.id, name, phone, cpf, birthDate, address, this.nextVersion()));
    }

    public void delete() {
        recordAndApply(new OwnerWasDeleted(this.id, this.nextVersion()));
    }

    protected void when(OwnerWasRegistered event) {
        this.id = event.getEntityId();
        this.name = event.getName();
        this.cpf = event.getCpf();
        this.phone = event.getPhone();
        this.birthDate = event.getBirthDate();
        this.address = event.getAddress();
    }

    protected void when(OwnerWasUpdated event) {
        this.name = event.getName();
        this.cpf = event.getCpf();
        this.phone = event.getPhone();
        this.birthDate = event.getBirthDate();
        this.address = event.getAddress();
    }

    protected void when(OwnerWasDeleted event) {
        this.markDeleted();
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
