package com.xgh.model.internment.command.events;

import com.xgh.buildingblocks.event.Event;
import com.xgh.model.animal.command.AnimalId;
import com.xgh.model.bed.command.BedId;
import com.xgh.model.internment.command.InternmentId;
import com.xgh.model.valueobjects.command.Date;
import com.xgh.model.valueobjects.command.EntityVersion;

public class InternmentWasRegistered extends Event<InternmentId> {
	private static final long serialVersionUID = 2553347792033362364L;

	private BedId bedId;
	private AnimalId animalId;
	private Date busyAt;
	private Date busyUntil;

	protected InternmentWasRegistered() {

	}

	public InternmentWasRegistered(InternmentId id, BedId bedId, AnimalId animalId, Date busyAt, Date busyUntil,
			EntityVersion version) {
		super(id, version);
		this.bedId = bedId;
		this.animalId = animalId;
		this.busyAt = busyAt;
		this.busyUntil = busyUntil;
	}

	public BedId getBedId() {
		return bedId;
	}

	public AnimalId getAnimalId() {
		return animalId;
	}

	public Date getBusyAt() {
		return busyAt;
	}

	public Date getBusyUntil() {
		return busyUntil;
	}

}
