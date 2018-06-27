package com.xgh.model.command.supplier.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.supplier.SupplierId;
import com.xgh.model.command.valueobjects.*;

public final class RegisterSupplier implements Command {

	private static final long serialVersionUID = 7965777984144335313L;
	
	
	private final SupplierId id;
    private final Name name;       
    private final Document document;
    private final Phone phone;    
    private final Address address;
    private final Name distributionType;

    protected RegisterSupplier() {
        this.id = new SupplierId();
        this.name = null;
        this.document = null;
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
