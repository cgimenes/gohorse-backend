package com.xgh.xgh.laboratory.command.events;

import com.xgh.buildingblocks.EntityVersion;
import com.xgh.buildingblocks.Event;
import com.xgh.valueobjects.Name;
import com.xgh.valueobjects.Phone;
import com.xgh.xgh.laboratory.command.LaboratoryId;

public class LaboratoryWasUpdated extends Event<LaboratoryId> {
	private Name companyName;
	private Phone phone;

    public LaboratoryWasUpdated(LaboratoryId id, Name companyName, Phone phone, EntityVersion version) {
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
