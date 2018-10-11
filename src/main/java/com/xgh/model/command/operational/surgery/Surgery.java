package com.xgh.model.command.operational.surgery;

import com.xgh.model.command.operational.animal.AnimalId;
import com.xgh.model.command.operational.appointment.AppointmentId;
import com.xgh.model.command.operational.veterinary.VeterinaryId;
import java.time.LocalDateTime;

import com.xgh.buildingblocks.entity.AggregateRoot;
import com.xgh.exceptions.NullMandatoryArgumentException;
import com.xgh.model.command.operational.surgery.events.SurgeryWasCancelled;
import com.xgh.model.command.operational.surgery.events.SurgeryWasDeleted;
import com.xgh.model.command.operational.surgery.events.SurgeryWasFinished;
import com.xgh.model.command.operational.surgery.events.SurgeryWasRegistered;
import com.xgh.model.command.operational.surgery.events.SurgeryWasUpdated;

public final class Surgery extends AggregateRoot<SurgeryId> {
	private AnimalId animal;
	private VeterinaryId veterinary;
	private LocalDateTime dateTime;
	private String surgeryType;
	private String notes = "";
	private SurgeryStatus status;
	private AppointmentId appointment;

	public void register(SurgeryId id, AnimalId animal, VeterinaryId veterinary, LocalDateTime dateTime,
			String surgeryType, String notes, AppointmentId appointment) {
		if (id == null) {
			throw new NullMandatoryArgumentException("id");
		}

		if (animal == null) {
			throw new NullMandatoryArgumentException("Animal");
		}

		if (veterinary == null) {
			throw new NullMandatoryArgumentException("Veterinário");
		}

		if (dateTime == null) {
			throw new NullMandatoryArgumentException("Data e Hora");
		}

		if (surgeryType == null) {
			throw new NullMandatoryArgumentException("Tipo");
		}

		if (appointment == null) {
			throw new NullMandatoryArgumentException("Consulta");
		}

		recordAndApply(new SurgeryWasRegistered(id, animal, veterinary, dateTime, surgeryType, notes,
				SurgeryStatus.SCHEDULED, appointment, this.nextVersion()));
	}

	protected void when(SurgeryWasRegistered event) {
		this.animal = event.getAnimal();
		this.veterinary = event.getVeterinary();
		this.dateTime = event.getDateTime();
		this.surgeryType = event.getSurgeryType();
		this.notes = event.getNotes();
		this.status = event.getStatus();
		this.appointment = event.getAppointment();
	}

	protected void when(SurgeryWasCancelled event) {
		this.status = SurgeryStatus.CANCELLED;
	}

	protected void when(SurgeryWasUpdated event) {
		this.veterinary = event.getVeterinary();
		this.dateTime = event.getDateTime();
		this.surgeryType = event.getSurgeryType();
		this.notes = event.getNotes();
	}

	protected void when(SurgeryWasFinished event) {
		this.status = SurgeryStatus.FINISHED;
	}

	protected void when(SurgeryWasDeleted event) {
		this.markDeleted();
	}

	public void update(VeterinaryId veterinary, LocalDateTime dateTime, String surgeryType, String notes) {
		if (veterinary == null) {
			throw new NullMandatoryArgumentException("Veterinários");
		}

		if (dateTime == null) {
			throw new NullMandatoryArgumentException("Data e Hora");
		}

		if (surgeryType == null) {
			throw new NullMandatoryArgumentException("Tipo");
		}

		recordAndApply(new SurgeryWasUpdated(veterinary, dateTime, surgeryType, notes, this.id, this.nextVersion()));
	}

	public void delete() {
		recordAndApply(new SurgeryWasDeleted(this.id, this.nextVersion()));
	}

	public void finish() {
		recordAndApply(new SurgeryWasFinished(this.id, this.nextVersion()));
	}

	public void cancel() {
		recordAndApply(new SurgeryWasCancelled(this.id, this.nextVersion()));
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
		return appointment;
	}
}
