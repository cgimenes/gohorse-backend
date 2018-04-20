package com.xgh.model.command.animal.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.animal.AnimalId;
import com.xgh.model.command.owner.Owner;
import com.xgh.model.command.valueobjects.Date;
import com.xgh.model.command.valueobjects.Name;

public final class RegisterAnimal implements Command{
	private static final long serialVersionUID = -4460061586391159971L;
	
	private AnimalId id;
	private Name name;
	private Owner owner;
	private String breed;
	private Date birthDate;
	private Float weight;
	private boolean castrated;
	
	protected RegisterAnimal() {
		this.id = new AnimalId();
		this.name = null;
		this.owner = null;
		this.breed = null;
		this.birthDate = null;
		this.weight = null;
		this.castrated = false;
	}

	public AnimalId getId() {
		return id;
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
