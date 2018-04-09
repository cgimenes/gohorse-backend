package com.xgh.model.laboratory.command.events;

import com.xgh.buildingblocks.event.Event;
import com.xgh.model.laboratory.command.LaboratoryId;
import com.xgh.model.valueobjects.command.Address;
import com.xgh.model.valueobjects.command.EntityVersion;
import com.xgh.model.valueobjects.command.Name;
import com.xgh.model.valueobjects.command.Phone;

public class LaboratoryWasUpdated extends Event<LaboratoryId> {
	private static final long serialVersionUID = 8837633091108416524L;
	
	private Name companyName;
	private Phone phone;
	private Address address;

	protected LaboratoryWasUpdated() {}
	
    public LaboratoryWasUpdated(LaboratoryId id, Name companyName, Phone phone, Address address, EntityVersion version) {
        super(id, version);
        this.companyName = companyName;
        this.phone = phone;
		this.address = address;
    }
    
	public Name getCompanyName() {
		return this.companyName;
	}

	public Phone getPhone() {
		return this.phone;
	}

	public Address getAddress() {
		return this.address;
	}
}
