package com.xgh.xgh.laboratory.command.events;

import com.xgh.buildingblocks.Event;
import com.xgh.valueobjects.EntityVersion;
import com.xgh.valueobjects.Name;
import com.xgh.valueobjects.Phone;
import com.xgh.xgh.laboratory.command.LaboratoryId;

public class LaboratoryWasRegistered extends Event<LaboratoryId> {
	private static final long serialVersionUID = -1312267808691672113L;
	
	private Name companyName;
	private Phone phone;
	
	protected LaboratoryWasRegistered() {}
	
    public LaboratoryWasRegistered(LaboratoryId id, Name companyName, Phone phone, EntityVersion version) {
        super(id, version);
        this.companyName = companyName;
        this.phone = phone;
    }
    
	public Name getCompanyName() {
		return this.companyName;
	}

	public Phone getPhone() {
		return this.phone;
	}
}
