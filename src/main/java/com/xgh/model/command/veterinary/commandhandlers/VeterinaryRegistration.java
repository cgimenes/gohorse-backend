package com.xgh.model.command.veterinary.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.veterinary.Veterinary;
import com.xgh.model.command.veterinary.commands.RegisterVeterinary;

public class VeterinaryRegistration implements CommandHandler<RegisterVeterinary> {

	private final EventStore repository;

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
