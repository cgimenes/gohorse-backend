package com.xgh.model.command.operational.surgery.events;

import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.model.command.operational.veterinary.VeterinaryId;
import java.time.LocalDateTime;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.model.command.operational.surgery.SurgeryId;

public class SurgeryWasUpdated extends EntityEvent<SurgeryId> {
	private VeterinaryId veterinary;
	private LocalDateTime dateTime;
	private String surgeryType;
	private String notes;

	protected SurgeryWasUpdated() {
	}

	public SurgeryWasUpdated(VeterinaryId veterinary, LocalDateTime dateTime, String surgeryType, String notes,
			SurgeryId id, EntityVersion version) {
		super(id, version);
		this.veterinary = veterinary;
		this.dateTime = dateTime;
		this.surgeryType = surgeryType;
		this.notes = notes;
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

}
