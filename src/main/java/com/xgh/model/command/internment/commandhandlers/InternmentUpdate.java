package com.xgh.model.command.internment.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.internment.Internment;
import com.xgh.model.command.internment.commands.UpdateInternment;

public class InternmentUpdate implements CommandHandler<UpdateInternment> {
	private EventStore repository;

	public InternmentUpdate(EventStore repository) {
		this.repository = repository;
	}

	@Override
	public void execute(UpdateInternment command) {
		Internment internment = new Internment();
		internment.update(command.getBedId(), command.getAnimalId(), command.getBusyAt(), command.getBusyUntil());
		repository.push(internment);
	}

}
