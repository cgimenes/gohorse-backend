package com.xgh.model.laboratory.command.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.laboratory.command.LaboratoryId;
import com.xgh.model.valueobjects.Address;
import com.xgh.model.valueobjects.Name;
import com.xgh.model.valueobjects.Phone;

public final class RegisterLaboratory implements Command {
	private static final long serialVersionUID = 864620291981116409L;
	
	private final LaboratoryId id;
    private final Name companyName;
    private final Phone phone;
	private final Address address;

    protected RegisterLaboratory() {
        this.id = new LaboratoryId();
        this.companyName = null;
        this.phone = null;
        this.address = null;
    }

    public LaboratoryId getId() {
        return id;
    }

    public Name getCompanyName() {
        return companyName;
    }

    public Phone getPhone() {
        return phone;
    }

	public Address getAddress() {
		return address;
	}
}
