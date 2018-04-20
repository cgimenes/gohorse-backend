package com.xgh.model.command.internment.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.Event;
import com.xgh.model.command.animal.Animal;
import com.xgh.model.command.bed.Bed;
import com.xgh.model.command.internment.InternmentId;
import com.xgh.model.command.valueobjects.Date;

public class InternmentWasUpdated extends Event<InternmentId> {
	private static final long serialVersionUID = -3596563508703713510L;

	private Bed bed;
	private Animal animal;
	private Date busyAt;
	private Date busyUntil;

	protected InternmentWasUpdated() {

	}

	public InternmentWasUpdated(InternmentId id, Bed bed, Animal animal, Date busyAt, Date busyUntil,
			EntityVersion version) {
		super(id, version);
	}

	public Bed getBed() {
		return bed;
	}

	public Animal getAnimal() {
		return animal;
	}

	public Date getBusyAt() {
		return busyAt;
	}

	public Date getBusyUntil() {
		return busyUntil;
	}

}
