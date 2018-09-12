package com.xgh.model.command.operational.supplier.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.operational.supplier.SupplierId;
import com.xgh.model.command.operational.valueobjects.Address;
import com.xgh.model.command.operational.valueobjects.Document;
import com.xgh.model.command.operational.valueobjects.Name;
import com.xgh.model.command.operational.valueobjects.Phone;

public final class UpdateSupplier implements Command {
    private final SupplierId id;
    private final Name name;
    private final Document document;
    private final Phone phone;
    private final Address address;
    private final Name distributionType;

    protected UpdateSupplier() {
        this.id = null;
        this.name = null;
        this.document = null;
        this.phone = null;
        this.distributionType = null;
        this.address = null;
    }

    @Override
    public SupplierId getId() {
        return this.id;
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

    public Name getDistributionType() {
        return this.distributionType;
    }

    public Address getAddress() {
        return this.address;
    }
}
