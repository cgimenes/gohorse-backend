package com.xgh.xgh.veterinary.command.commandhandlers;

import com.xgh.buildingblocks.CommandHandler;
import com.xgh.buildingblocks.EventStore;
import com.xgh.xgh.veterinary.command.Veterinary;
import com.xgh.xgh.veterinary.command.commands.UpdateVeterinary;

public class VeterinaryUpdate implements CommandHandler<UpdateVeterinary> {

	private EventStore repository;

	public VeterinaryUpdate(EventStore repository) {
		this.repository = repository;
	}

	@Override
	public void execute(UpdateVeterinary command) {
		Veterinary veterinary = repository.pull(Veterinary.class, command.getId());
		veterinary.update(command.getName(), command.getAddress(), command.getPhone(), command.getCrmv(), command.getEmail(),
				command.getBirthDate());
		repository.push(veterinary);
	}

}
