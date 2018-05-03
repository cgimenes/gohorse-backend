package com.xgh.model.command.bed.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.bed.Bed;
import com.xgh.model.command.bed.commands.RegisterBed;

public class BedRegistration implements CommandHandler<RegisterBed> {

	private final EventStore repository;
	
	public BedRegistration(EventStore repository) {
		this.repository = repository;
	}
	
	@Override
	public void execute(RegisterBed command) {
		Bed bed = new Bed();
		bed.register(command.getId(), command.getCode(), command.getBusy());
		repository.push(bed);
	}
}
