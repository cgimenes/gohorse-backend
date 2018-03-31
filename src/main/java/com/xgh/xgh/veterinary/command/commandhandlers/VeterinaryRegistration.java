package com.xgh.xgh.veterinary.command.commandhandlers;

import com.xgh.buildingblocks.CommandHandler;
import com.xgh.buildingblocks.EventStore;
import com.xgh.xgh.veterinary.command.Veterinary;
import com.xgh.xgh.veterinary.command.commands.RegisterVeterinary;

public class VeterinaryRegistration implements CommandHandler<RegisterVeterinary> {

	private EventStore repository;

	public VeterinaryRegistration(EventStore repository) {
		this.repository = repository;
	}

	@Override
	public void execute(RegisterVeterinary command) {
		Veterinary veterinary = new Veterinary();
		veterinary.register(command.getId(), command.getName(), command.getPhone(), command.getCrmv(),
				command.getMail(), command.getBirthDate(), command.isActive());
		repository.push(veterinary);
	}
}
