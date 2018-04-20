package com.xgh.model.command.animal.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.Event;
import com.xgh.model.command.animal.AnimalId;
import com.xgh.model.command.owner.Owner;
import com.xgh.model.command.valueobjects.Date;
import com.xgh.model.command.valueobjects.Name;

public class AnimalWasRegistered extends Event<AnimalId> {
	private static final long serialVersionUID = 8023225048402048975L;
	
	private Name name;
	private Owner owner;
	private String breed;
	private Date birthDate;
	private Float weight;
	private boolean castrated;
	
	protected AnimalWasRegistered() {}

	public AnimalWasRegistered(AnimalId id, Name name, Owner owner, String breed, Date birthDate, Float weight, boolean castrated, EntityVersion version) {
		super(id, version);
		this.name = name;
		this.owner = owner;
		this.breed = breed;
		this.birthDate = birthDate;
		this.weight = weight;
		this.castrated = castrated;
	}

	public Name getName() {
		return name;
	}

	public Owner getOwner() {
		return owner;
	}

	public String getBreed() {
		return breed;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public Float getWeight() {
		return weight;
	}

	public boolean isCastrated() {
		return castrated;
	}
}
