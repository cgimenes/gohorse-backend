package com.xgh.model.laboratory.command.events;

import com.xgh.buildingblocks.event.Event;
import com.xgh.model.laboratory.command.LaboratoryId;
import com.xgh.model.valueobjects.Address;
import com.xgh.model.valueobjects.EntityVersion;
import com.xgh.model.valueobjects.Name;
import com.xgh.model.valueobjects.Phone;

public class LaboratoryWasRegistered extends Event<LaboratoryId> {
	private static final long serialVersionUID = -1312267808691672113L;
	
	private Name companyName;
	private Phone phone;
	private Address address;
	
	protected LaboratoryWasRegistered() {}
	
    public LaboratoryWasRegistered(LaboratoryId id, Name companyName, Phone phone, Address address, EntityVersion version) {
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
