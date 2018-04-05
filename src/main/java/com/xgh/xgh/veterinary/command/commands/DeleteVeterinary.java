package com.xgh.xgh.veterinary.command.commands;

import com.xgh.buildingblocks.Command;
import com.xgh.xgh.veterinary.command.VeterinaryId;

public class DeleteVeterinary extends Command {
	private static final long serialVersionUID = -3191539102394257532L;

	private final VeterinaryId id;
	
	public DeleteVeterinary() {
		this.id = null;
	}
	
	public VeterinaryId getId() {
		return this.id;
	}
}
