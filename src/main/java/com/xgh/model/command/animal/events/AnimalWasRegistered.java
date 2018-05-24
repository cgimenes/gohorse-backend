package com.xgh.model.command.animal.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.Event;
import com.xgh.model.command.animal.AnimalId;
import com.xgh.model.command.owner.OwnerId;
import com.xgh.model.command.valueobjects.Date;
import com.xgh.model.command.valueobjects.Name;
import com.xgh.model.command.valueobjects.Sex;

public class AnimalWasRegistered extends Event<AnimalId> {
	private Name name;
	private OwnerId owner;
	private Name breed;
	private Name specie;
	private Sex sex;
	private Date birthDate;
	private Float weight;
	private boolean castrated;
	
	protected AnimalWasRegistered() {}

	public AnimalWasRegistered(AnimalId id, Name name, OwnerId owner, Name breed, Name specie, Sex sex, Date birthDate, Float weight, boolean castrated, EntityVersion version) {
		super(id, version);
		this.name = name;
		this.owner = owner;
		this.breed = breed;
		this.specie = specie;
		this.sex = sex;
		this.birthDate = birthDate;
		this.weight = weight;
		this.castrated = castrated;
	}

	public Name getName() {
		return name;
	}

	public OwnerId getOwner() {
		return owner;
	}

	public Name getBreed() {
		return breed;
	}
	
	public Name getSpecie() {
		return specie;
	}
	
	public Sex getSex() {
		return sex;
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
