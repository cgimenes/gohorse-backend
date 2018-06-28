package com.xgh.model.command.supplier;

import com.xgh.buildingblocks.entity.AggregateRoot;
import com.xgh.exceptions.NullMandatoryArgumentException;
import com.xgh.model.command.supplier.events.SupplierWasDeleted;
import com.xgh.model.command.supplier.events.SupplierWasRegistered;
import com.xgh.model.command.supplier.events.SupplierWasUpdated;
import com.xgh.model.command.valueobjects.Address;
import com.xgh.model.command.valueobjects.Name;
import com.xgh.model.command.valueobjects.Phone;

public class Supplier extends AggregateRoot<SupplierId> {
    private Name name;
    private Phone phone;
    private String document;
    private Address address;
    private Name distributionType;

    public void register(SupplierId id, Name name, Phone phone, String document, Address address,
                         Name distributionType) {
        if (id == null) {
            throw new NullMandatoryArgumentException("ID");
        }

        if (name == null) {
            throw new NullMandatoryArgumentException("Nome");
        }

        if (phone == null) {
            throw new NullMandatoryArgumentException("Telefone");
        }

        if (document == null) {
            throw new NullMandatoryArgumentException("CPF/CNPJ");
        }

        if (address == null) {
            throw new NullMandatoryArgumentException("Endereço");
        }

        if (distributionType == null) {
            throw new NullMandatoryArgumentException("Tipo de distribuição");
        }

        recordAndApply(
                new SupplierWasRegistered(id, name, phone, document, address, distributionType, this.nextVersion()));
    }

    public void update(Name name, Phone phone, String document, Address address, Name distributionType) {
        recordAndApply(
                new SupplierWasUpdated(this.id, name, phone, document, address, distributionType, this.nextVersion()));
    }

    public void delete() {
        recordAndApply(new SupplierWasDeleted(this.id, this.nextVersion()));
    }

    protected void when(SupplierWasRegistered event) {
        this.id = event.getEntityId();
        this.name = event.getName();
        this.document = event.getDocument();
        this.phone = event.getPhone();
        this.address = event.getAddress();
        this.distributionType = event.getDistributionType();
    }

    protected void when(SupplierWasUpdated event) {
        this.name = event.getName();
        this.document = event.getDocument();
        this.phone = event.getPhone();
        this.address = event.getAddress();
        this.distributionType = event.getDistributionType();
    }

    protected void when(SupplierWasDeleted event) {
        this.markDeleted();
    }

    public Name getName() {
        return this.name;
    }

    public String getDocument() {
        return this.document;
    }

    public Phone getPhone() {
        return this.phone;
    }

    public Name getDistributionType() {
        return this.distributionType;
    }

    public Address getAddress() {
        return this.address;
    }
}
