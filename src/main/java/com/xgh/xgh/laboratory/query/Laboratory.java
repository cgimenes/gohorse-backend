package com.xgh.xgh.laboratory.query;

import com.xgh.buildingblocks.Entity;
import com.xgh.buildingblocks.EntityId;
import com.xgh.valueobjects.Name;
import com.xgh.valueobjects.Phone;

public final class Laboratory extends Entity<EntityId> {
    private Name companyName;
	private Phone phone;

    public Laboratory(EntityId id, Name companyName, Phone phone) {
        this.id = id;
        this.companyName = companyName;
        this.phone = phone;
    }
    
    public Name getCompanyName() {
		return companyName;
	}

	public Phone getPhone() {
		return phone;
	}
}
