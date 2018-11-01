package com.xgh.model.command.operational.surgery.commands;

import com.xgh.buildingblocks.command.EntityCommand;
import com.xgh.model.command.operational.veterinary.VeterinaryId;
import java.time.LocalDateTime;

import com.xgh.model.command.operational.surgery.SurgeryId;

public class UpdateSurgery implements EntityCommand {
	private SurgeryId id;
	private VeterinaryId veterinary;
	private LocalDateTime dateTime;
	private String surgeryType;
	private String notes;
	
	@Override
	public SurgeryId getId() {
		return this.id;
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
