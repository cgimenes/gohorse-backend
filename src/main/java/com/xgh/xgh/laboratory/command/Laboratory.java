package com.xgh.xgh.laboratory.command;

import com.xgh.buildingblocks.AggregateRoot;
import com.xgh.exceptions.NullMandatoryArgumentException;
import com.xgh.valueobjects.Name;
import com.xgh.valueobjects.Phone;
import com.xgh.xgh.laboratory.command.events.LaboratoryWasDeleted;
import com.xgh.xgh.laboratory.command.events.LaboratoryWasRegistered;
import com.xgh.xgh.laboratory.command.events.LaboratoryWasUpdated;

public final class Laboratory extends AggregateRoot<LaboratoryId> {
    private Name companyName;
	private Phone phone;

    public void register(LaboratoryId id, Name companyName, Phone phone) {
    	if (companyName == null) {
    		throw new NullMandatoryArgumentException("nome");
    	}
    	
    	if (phone == null) {
    		throw new NullMandatoryArgumentException("telefone");
    	}
    	
    	recordAndApply(new LaboratoryWasRegistered(id, companyName, phone, this.nextVersion()));
    }

    public void update(Name companyName, Phone phone) {
    	recordAndApply(new LaboratoryWasUpdated(this.id, companyName, phone, this.nextVersion()));
    }
    
    public void delete() {
    	recordAndApply(new LaboratoryWasDeleted(this.id, this.nextVersion()));
    }

    protected void when(LaboratoryWasRegistered event) {
    	this.companyName = event.getCompanyName();
    	this.phone = event.getPhone();
    }

    protected void when(LaboratoryWasUpdated event) {
    	this.companyName = event.getCompanyName();
    	this.phone = event.getPhone();
    }
    
    protected void when(LaboratoryWasDeleted event) {
    	this.markDeleted();
    }

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
