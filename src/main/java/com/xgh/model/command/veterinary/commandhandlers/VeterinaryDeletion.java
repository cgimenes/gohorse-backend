package com.xgh.model.command.veterinary.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.veterinary.Veterinary;
import com.xgh.model.command.veterinary.commands.DeleteVeterinary;

public class VeterinaryDeletion implements CommandHandler<DeleteVeterinary>{

	private final EventStore repository;
	
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
