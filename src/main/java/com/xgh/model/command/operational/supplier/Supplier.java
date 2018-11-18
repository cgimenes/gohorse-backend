package com.xgh.model.command.operational.supplier;

import com.xgh.buildingblocks.entity.AggregateRoot;
import com.xgh.exceptions.NullMandatoryArgumentException;
import com.xgh.model.command.operational.enumerator.EnumeratorId;
import com.xgh.model.command.operational.supplier.events.SupplierWasDeleted;
import com.xgh.model.command.operational.supplier.events.SupplierWasRegistered;
import com.xgh.model.command.operational.supplier.events.SupplierWasUpdated;
import com.xgh.model.command.operational.valueobjects.Address;
import com.xgh.model.command.operational.valueobjects.Name;
import com.xgh.model.command.operational.valueobjects.Phone;

public class Supplier extends AggregateRoot<SupplierId> {
    private Name name;
    private Phone phone;
    private String document;
    private Address address;
    private EnumeratorId distributionType;

    public void register(SupplierId id, Name name, Phone phone, EnumeratorId distributionType, String document, Address address) {
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
                new SupplierWasRegistered(id, name, phone, distributionType, document, address, this.nextVersion()));
    }

    public void update(Name name, Phone phone, EnumeratorId distributionType, String document, Address address) {
        recordAndApply(
                new SupplierWasUpdated(this.id, name, phone, distributionType, document, address, this.nextVersion()));
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

    public EnumeratorId getDistributionType() {
        return this.distributionType;
    }

    public Address getAddress() {
        return this.address;
    }
}
