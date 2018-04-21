package com.xgh.model.command.supplier.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.supplier.SupplierId;
import com.xgh.model.command.valueobjects.*;

public final class UpdateSupplier implements Command {
    private static final long serialVersionUID = -5468719672345592356L;

    private final SupplierId id;
    private final Name name;
    private final CpfCnpj cpfCnpj;
    private final Phone phone;    
    private final Address address;
    private final DistributionType distributionType;

    protected UpdateSupplier() {
        this.id = null;
        this.name = null;
        this.cpfCnpj = null;
        this.phone = null;
        this.distributionType = null;
        this.address = null;
    }

    public SupplierId getId() {
        return this.id;
    }

    public Name getName() {
        return this.name;
    }

    public CpfCnpj getCpfCnpj() {
        return this.cpfCnpj;
    }

    public Phone getPhone() {
        return this.phone;
    }

    public DistributionType getDistributionType() {
        return this.distributionType;
    }

    public Address getAddress() {
        return this.address;
    }
}
