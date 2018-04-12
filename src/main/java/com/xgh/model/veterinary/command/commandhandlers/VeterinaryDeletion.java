package com.xgh.model.veterinary.command.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.veterinary.command.Veterinary;
import com.xgh.model.veterinary.command.commands.DeleteVeterinary;

public class VeterinaryDeletion implements CommandHandler<DeleteVeterinary>{

	private EventStore repository;
	
	public VeterinaryDeletion(EventStore repository) {
		this.repository = repository;
	}
	
	@Override
	public void execute(DeleteVeterinary command) {
		Veterinary veterinary = repository.pull(Veterinary.class, command.getId());
		veterinary.delete();
		repository.push(veterinary);
	}
}
