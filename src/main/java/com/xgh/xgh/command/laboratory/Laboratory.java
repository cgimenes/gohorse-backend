package com.xgh.xgh.command.laboratory;

import com.xgh.buildingblocks.Entity;
import com.xgh.buildingblocks.EventBus;
import com.xgh.valueobjects.Name;
import com.xgh.valueobjects.Phone;
import com.xgh.xgh.command.laboratory.events.LaboratoryWasRegistered;
import com.xgh.xgh.command.laboratory.events.LaboratoryWasUpdated;

public final class Laboratory extends Entity<LaboratoryId> {
    private Name name;
	private Phone phone;

    public static Laboratory register(LaboratoryId id, Name name, Phone phone) {
        Laboratory instance = new Laboratory();
        instance.id = id;
        instance.name = name;
        instance.phone = phone;

        EventBus.dispatch(new LaboratoryWasRegistered(id));
        return instance;
    }
    
    public void update(Name name, Phone phone) {
    	this.name = name;
    	this.phone = phone;

        EventBus.dispatch(new LaboratoryWasUpdated(id));
    }

    // Construtor para hydration
    public Laboratory(LaboratoryId id, Name name, Phone phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    // Construtor para registration
    private Laboratory() {
    }
    
    public Name getName() {
		return name;
	}

	public Phone getPhone() {
		return phone;
	}
}
