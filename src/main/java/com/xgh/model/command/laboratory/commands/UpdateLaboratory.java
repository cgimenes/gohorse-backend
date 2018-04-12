package com.xgh.model.command.laboratory.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.laboratory.LaboratoryId;
import com.xgh.model.command.valueobjects.Address;
import com.xgh.model.command.valueobjects.Name;
import com.xgh.model.command.valueobjects.Phone;

public final class UpdateLaboratory implements Command {
	private static final long serialVersionUID = -4910695718010490673L;
	
	private final LaboratoryId id;
    private final Name companyName;
    private final Phone phone;
	private final Address address;

    protected UpdateLaboratory() {
        this.id = null;
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