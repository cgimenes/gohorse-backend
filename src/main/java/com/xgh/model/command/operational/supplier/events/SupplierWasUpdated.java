package com.xgh.model.command.operational.supplier.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.Event;
import com.xgh.model.command.operational.supplier.SupplierId;
import com.xgh.model.command.operational.valueobjects.Address;
import com.xgh.model.command.operational.valueobjects.Name;
import com.xgh.model.command.operational.valueobjects.Phone;

public class SupplierWasUpdated extends Event<SupplierId> {
    private Name name;
    private Phone phone;
    private String document;
    private Address address;
    private Name distributionType;

    protected SupplierWasUpdated() {
    }

    public SupplierWasUpdated(
            SupplierId id,
            Name name,
            Phone phone,
            String document,
            Address address,
            Name distributionType,
            EntityVersion version
    ) {
        super(id, version);
        this.name = name;
        this.phone = phone;
        this.document = document;
        this.address = address;
        this.distributionType = distributionType;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public String getDocument() {
        return document;
    }

    public Name getDistributionType() {
        return distributionType;
    }

    public Address getAddress() {
        return address;
    }
}
