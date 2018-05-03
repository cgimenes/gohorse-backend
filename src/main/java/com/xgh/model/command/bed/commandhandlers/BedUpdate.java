package com.xgh.model.command.bed.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.bed.Bed;
import com.xgh.model.command.bed.commands.UpdateBed;


public class BedUpdate implements CommandHandler<UpdateBed>{

	private final EventStore repository;
	
	public BedUpdate(EventStore repository) {
		this.repository = repository;
	}
	
	@Override
	public void execute(UpdateBed command) {
		Bed bed = repository.pull(Bed.class , command.getId());
		bed.update(command.getCode(), command.getBusy());
		repository.push(bed);
		
	}
}
