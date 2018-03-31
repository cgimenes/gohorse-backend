package com.xgh.xgh.laboratory.command.events;

import com.xgh.buildingblocks.Event;
import com.xgh.valueobjects.EntityVersion;
import com.xgh.valueobjects.Name;
import com.xgh.valueobjects.Phone;
import com.xgh.xgh.laboratory.command.LaboratoryId;

public class LaboratoryWasUpdated extends Event<LaboratoryId> {
	private static final long serialVersionUID = 8837633091108416524L;
	
	private Name companyName;
	private Phone phone;

	public LaboratoryWasUpdated() {		
	}
	
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
