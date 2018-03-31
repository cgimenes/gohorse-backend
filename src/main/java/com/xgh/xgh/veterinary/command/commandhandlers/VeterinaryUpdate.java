package com.xgh.xgh.veterinary.commandmodel.commandhandlers;

import com.xgh.buildingblocks.CommandHandler;
import com.xgh.buildingblocks.EventStore;
import com.xgh.xgh.veterinary.commandmodel.Veterinary;
import com.xgh.xgh.veterinary.commandmodel.commands.UpdateVeterinary;

public class VeterinaryUpdate implements CommandHandler<UpdateVeterinary> {

	private EventStore repository;

	public VeterinaryUpdate(EventStore repository) {
		this.repository = repository;
	}

	@Override
	public void execute(UpdateVeterinary command) {
		Veterinary veterinary = repository.pull(Veterinary.class, command.getId());
		veterinary.update(command.getName(), command.getPhone(), command.getCrmv(), command.getMail(),
				command.getBirthDate(), command.isActive());
		repository.push(veterinary);
	}

}
