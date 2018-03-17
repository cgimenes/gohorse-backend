package com.xgh.xgh.command.laboratory.events;

import com.xgh.buildingblocks.EntityVersion;
import com.xgh.buildingblocks.Event;
import com.xgh.valueobjects.Name;
import com.xgh.valueobjects.Phone;
import com.xgh.xgh.command.laboratory.LaboratoryId;

public class LaboratoryWasRegistered extends Event<LaboratoryId> {
	private Name companyName;
	private Phone phone;

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
