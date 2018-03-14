package com.xgh.xgh.query.laboratory;

import com.xgh.buildingblocks.Entity;
import com.xgh.valueobjects.Name;
import com.xgh.valueobjects.Phone;

public final class Laboratory extends Entity<LaboratoryId> {
    private Name name;
	private Phone phone;

    public Laboratory(LaboratoryId id, Name name, Phone phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }
    
    public Name getName() {
		return name;
	}

	public Phone getPhone() {
		return phone;
	}
}
