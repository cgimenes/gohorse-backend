package com.xgh.model.command.veterinary.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.veterinary.VeterinaryId;

public class DeleteVeterinary implements Command {
	private static final long serialVersionUID = -3191539102394257532L;

	private final VeterinaryId id;
	
	protected DeleteVeterinary() {
		this.id = null;
	}
	
	public VeterinaryId getId() {
		return this.id;
	}
}
