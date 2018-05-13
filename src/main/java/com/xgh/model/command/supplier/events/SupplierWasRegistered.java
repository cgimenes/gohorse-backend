package com.xgh.model.command.supplier.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.Event;
import com.xgh.model.command.supplier.SupplierId;
import com.xgh.model.command.valueobjects.*;

public class SupplierWasRegistered extends Event<SupplierId> {
    private static final long serialVersionUID = -3062625947435023015L;

    private Name name;
    private Phone phone;
    private Document cpfCnpj;
    private Address address;
    private Name distributionType;

    protected SupplierWasRegistered() {}

    public SupplierWasRegistered(SupplierId id, Name name, Phone phone, Document cpfCnpj, Address address, Name distributionType, EntityVersion version) {
        super(id, version);
        this.name = name;
        this.phone = phone;
        this.cpfCnpj = cpfCnpj;
        this.address = address;
        this.distributionType = distributionType;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Document getCpfCnpj() {
        return cpfCnpj;
    }

    public Name getDistributionType() {
        return distributionType;
    }

    public Address getAddress() {
        return address;
    }
}
