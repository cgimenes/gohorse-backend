package com.xgh.model.veterinary.command.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.veterinary.command.Veterinary;
import com.xgh.model.veterinary.command.commands.RegisterVeterinary;

public class VeterinaryRegistration implements CommandHandler<RegisterVeterinary> {

	private EventStore repository;

	public VeterinaryRegistration(EventStore repository) {
		this.repository = repository;
	}

	@Override
	public void execute(RegisterVeterinary command) {
		Veterinary veterinary = new Veterinary();
		veterinary.register(command.getId(), command.getName(), command.getAddress(), command.getPhone(), 
				command.getCrmv(), command.getEmail(), command.getBirthDate());
		repository.push(veterinary);
	}
}
