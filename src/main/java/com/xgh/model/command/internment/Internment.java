package com.xgh.model.command.internment;

import com.xgh.buildingblocks.entity.AggregateRoot;
import com.xgh.exceptions.NullMandatoryArgumentException;
import com.xgh.model.command.animal.Animal;
import com.xgh.model.command.bed.Bed;
import com.xgh.model.command.internment.events.InternmentWasDeleted;
import com.xgh.model.command.internment.events.InternmentWasRegistered;
import com.xgh.model.command.internment.events.InternmentWasUpdated;
import com.xgh.model.command.valueobjects.Date;

public class Internment extends AggregateRoot<InternmentId> {
	private static final long serialVersionUID = 513769173122252062L;

	private Bed bed;
	private Animal animal;
	private Date busyAt;
	private Date busyUntil;

	public Internment() {
		super();
	}

	public void register(InternmentId id, Bed bed, Animal animal, Date busyAt, Date busyUntil) {
		if (id == null) {
			throw new NullMandatoryArgumentException("id");
		}

		if (bed == null) {
			throw new NullMandatoryArgumentException("leito");
		}

		if (animal == null) {
			throw new NullMandatoryArgumentException("animal");
		}

		if (busyAt == null) {
			throw new NullMandatoryArgumentException("data de entrada");
		}

		recordAndApply(new InternmentWasRegistered(id, bed, animal, busyAt, busyUntil, this.nextVersion()));
	}
	
	public void update(Bed bedId, Animal animalId, Date busyAt, Date busyUntil) {
		recordAndApply(new InternmentWasUpdated(this.id, bedId, animalId, busyAt, busyUntil, this.nextVersion()));
	}
	
	public void delete() {
		recordAndApply(new InternmentWasDeleted(this.id, this.nextVersion()));
	}
	
    protected void when(InternmentWasRegistered event) {
    	this.bed = event.getBed();
    	this.animal = event.getAnimal();
    	this.busyAt = event.getBusyAt();
    	this.busyUntil = event.getBusyUntil();
    }

    protected void when(InternmentWasUpdated event) {
    	this.bed = event.getBed();
    	this.animal = event.getAnimal();
    	this.busyAt = event.getBusyAt();
    	this.busyUntil = event.getBusyUntil();
    }
    
    protected void when(InternmentWasDeleted event) {
    	this.markDeleted();
    }

	public Bed getBedId() {
		return bed;
	}

	public Animal getAnimalId() {
		return animal;
	}

	public Date getBusyAt() {
		return busyAt;
	}

	public Date getBusyUntil() {
		return busyUntil;
	}

}
