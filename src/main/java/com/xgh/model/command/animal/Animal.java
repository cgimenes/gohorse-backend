package com.xgh.model.command.animal;

import com.xgh.buildingblocks.entity.AggregateRoot;
import com.xgh.exceptions.NullMandatoryArgumentException;
import com.xgh.model.command.animal.events.AnimalWasRegistered;
import com.xgh.model.command.owner.Owner;
import com.xgh.model.command.valueobjects.*;

public final class Animal extends AggregateRoot<AnimalId> {
	private static final long serialVersionUID = 3118505285619458826L;

	private Name name;
	private Owner owner;
	private String breed;
	private Date birthDate;
	private Float weight;
	private boolean castrated = false;
	
	public Animal() {
		super();
	}
	
	public void register(AnimalId id, Name name, Owner owner, String breed, Date birthDate, Float weight, boolean castrated) {
		if(id == null) {
			throw new NullMandatoryArgumentException("id");
		}
		
		if(name == null) {
			throw new NullMandatoryArgumentException("Nome");
		}
		
		if(owner == null) {
			throw new NullMandatoryArgumentException("Proprietario");
		}
		
		if(breed == null){
			throw new NullMandatoryArgumentException("Raça");
		}
		
		if(birthDate == null) {
			throw new NullMandatoryArgumentException("Data de Nascimento");
		}
		
		recordAndApply(new AnimalWasRegistered(id, name, owner, breed, birthDate, weight, castrated, this.nextVersion()));
	}
	
	protected void when(AnimalWasRegistered event) {
		this.id = event.getEntityId();
		this.name = event.getName();
		this.breed = event.getBreed();
		this.birthDate = event.getBirthDate();
		this.weight = event.getWeight();
		this.castrated = event.isCastrated();
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
