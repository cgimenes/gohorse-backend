package com.xgh.xgh.query.laboratory;

import com.xgh.buildingblocks.Entity;
import com.xgh.valueobjects.Id;
import com.xgh.valueobjects.Name;
import com.xgh.valueobjects.Phone;

public final class Laboratory extends Entity<Id> {
    private Name companyName;
	private Phone phone;

    public Laboratory(Id id, Name companyName, Phone phone) {
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
