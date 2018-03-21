package com.xgh.xgh.laboratory.command;

import com.xgh.buildingblocks.Entity;
import com.xgh.buildingblocks.EntityVersion;
import com.xgh.valueobjects.Name;
import com.xgh.valueobjects.Phone;
import com.xgh.xgh.laboratory.command.events.LaboratoryWasRegistered;
import com.xgh.xgh.laboratory.command.events.LaboratoryWasUpdated;

public final class Laboratory extends Entity<LaboratoryId> {
    private Name companyName;
	private Phone phone;

    public void register(LaboratoryId id, Name companyName, Phone phone) {
    	recordAndApply(new LaboratoryWasRegistered(id, companyName, phone, this.nextVersion()));
    }
    
    public void update(Name companyName, Phone phone) {
    	recordAndApply(new LaboratoryWasUpdated(this.id, companyName, phone, this.nextVersion()));
    }
    
    protected void when(LaboratoryWasRegistered event) {
        this.id = event.getEntityId();
    	this.companyName = event.getCompanyName();
    	this.phone = event.getPhone();
    }
    
    protected void when(LaboratoryWasUpdated event) {
    	this.companyName = event.getCompanyName();
    	this.phone = event.getPhone();
    }

    // Construtor para hydration
    public Laboratory(LaboratoryId id, EntityVersion version, Name companyName, Phone phone) {
        this.id = id;
        this.version = version;
        this.companyName = companyName;
        this.phone = phone;
    }

    // Construtor blank
    public Laboratory() {
    	super();
    }
    
    public Name getCompanyName() {
		return companyName;
	}

	public Phone getPhone() {
		return phone;
	}
}
