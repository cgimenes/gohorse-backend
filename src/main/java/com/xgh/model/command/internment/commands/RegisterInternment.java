package com.xgh.model.command.internment.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.animal.Animal;
import com.xgh.model.command.bed.Bed;
import com.xgh.model.command.internment.InternmentId;
import com.xgh.model.command.valueobjects.Date;

public class RegisterInternment implements Command {
	private static final long serialVersionUID = -4390730779141246809L;

	private final InternmentId id;
	private final Bed bed;
	private final Animal animal;
	private final Date busyAt;
	private final Date busyUntil;

	public RegisterInternment() {
		this.id = null;
		this.bed = null;
		this.animal = null;
		this.busyAt = null;
		this.busyUntil = null;
	}

	public InternmentId getId() {
		return id;
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
