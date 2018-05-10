package com.xgh.model.command.supplier.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.Event;
import com.xgh.model.command.supplier.SupplierId;
import com.xgh.model.command.valueobjects.*;

public class SupplierWasUpdated extends Event<SupplierId> {
    private static final long serialVersionUID = 9166049599376937830L;

    private Name name;
    private Phone phone;
    private CpfCnpj cpfCnpj;
    private Address address;
    private Name distributionType;

    protected SupplierWasUpdated() {}

    public SupplierWasUpdated(SupplierId id, Name name, Phone phone, CpfCnpj cpfCnpj, Address address, Name distributionType, EntityVersion version) {
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

    public CpfCnpj getCpfCnpj() {
        return cpfCnpj;
    }

    public Name getDistributionType() {
        return distributionType;
    }

    public Address getAddress() {
        return address;
    }
}
