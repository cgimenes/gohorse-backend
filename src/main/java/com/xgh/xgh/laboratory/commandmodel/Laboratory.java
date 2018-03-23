package com.xgh.xgh.laboratory.commandmodel;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.xgh.buildingblocks.DomainEntity;
import com.xgh.valueobjects.Name;
import com.xgh.valueobjects.Phone;
import com.xgh.xgh.laboratory.commandmodel.events.LaboratoryWasRegistered;
import com.xgh.xgh.laboratory.commandmodel.events.LaboratoryWasUpdated;

@Entity
@Table(name = "laboratory")
public final class Laboratory extends DomainEntity<LaboratoryId> {
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "company_name"))
    private Name companyName;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "phone"))
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
