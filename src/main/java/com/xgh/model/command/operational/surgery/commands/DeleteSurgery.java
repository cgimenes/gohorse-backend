package com.xgh.model.command.operational.surgery.commands;

import com.xgh.buildingblocks.command.EntityCommand;
import com.xgh.model.command.operational.surgery.SurgeryId;

public class DeleteSurgery implements EntityCommand {
	private final SurgeryId id;
	
	protected DeleteSurgery() {
		this.id = null;
	}
	
	@Override
	public SurgeryId getId() {
		return this.id;
	}
}
