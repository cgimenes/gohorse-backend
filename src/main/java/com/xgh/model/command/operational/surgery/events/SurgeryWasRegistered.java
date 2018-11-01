package com.xgh.model.command.operational.surgery.events;

import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.model.command.operational.animal.AnimalId;
import com.xgh.model.command.operational.appointment.AppointmentId;
import com.xgh.model.command.operational.veterinary.VeterinaryId;
import java.time.LocalDateTime;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.model.command.operational.surgery.SurgeryId;
import com.xgh.model.command.operational.surgery.SurgeryStatus;

public class SurgeryWasRegistered extends EntityEvent<SurgeryId> {
	private AnimalId animal;
	private VeterinaryId veterinary;
	private LocalDateTime dateTime;
	private String surgeryType;
	private String notes;
	private SurgeryStatus status;
	private AppointmentId appointment;

	protected SurgeryWasRegistered() {
	}

	public SurgeryWasRegistered(SurgeryId id, AnimalId animal, VeterinaryId veterinary, LocalDateTime dateTime,
			String surgeryType, String notes, SurgeryStatus status, AppointmentId appointment, EntityVersion version) {
		super(id, version);
		this.animal = animal;
		this.veterinary = veterinary;
		this.dateTime = dateTime;
		this.surgeryType = surgeryType;
		this.notes = notes;
		this.status = status;
		this.appointment = appointment;
	}

	public AnimalId getAnimal() {
		return animal;
	}

	public VeterinaryId getVeterinary() {
		return veterinary;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public String getSurgeryType() {
		return surgeryType;
	}

	public String getNotes() {
		return notes;
	}

	public SurgeryStatus getStatus() {
		return status;
	}

	public AppointmentId getAppointment() {
		return this.appointment;
	}
}
