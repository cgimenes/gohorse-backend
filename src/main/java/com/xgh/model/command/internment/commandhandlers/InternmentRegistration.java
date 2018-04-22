package com.xgh.model.command.internment.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.internment.Internment;
import com.xgh.model.command.internment.commands.RegisterInternment;

public class InternmentRegistration implements CommandHandler<RegisterInternment> {

	private final EventStore repository;

	public InternmentRegistration(EventStore repository) {
		this.repository = repository;
	}

	@Override
	public void execute(RegisterInternment command) {
		Internment internment = new Internment();
		internment.register(command.getId(), command.getBedId(), command.getAnimalId(), command.getBusyAt(),
				command.getBusyUntil());
		repository.push(internment);
	}

}
