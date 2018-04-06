package com.xgh.xgh.veterinary.command.commandhandlers;

import com.xgh.buildingblocks.CommandHandler;
import com.xgh.buildingblocks.EventStore;
import com.xgh.xgh.veterinary.command.Veterinary;
import com.xgh.xgh.veterinary.command.commands.DeleteVeterinary;

public class VeterinaryDeletion implements CommandHandler<DeleteVeterinary>{

	private EventStore repository;
	
	public VeterinaryDeletion (EventStore repository) {
		this.repository = repository;
	}
	
	@Override
	public void execute(DeleteVeterinary command) {
		Veterinary veterinary = repository.pull(Veterinary.class, command.getId());
		veterinary.delete();
		repository.push(veterinary);
	}
}
