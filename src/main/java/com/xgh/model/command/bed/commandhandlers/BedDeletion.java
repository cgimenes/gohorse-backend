package com.xgh.model.command.bed.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.bed.Bed;
import com.xgh.model.command.bed.commands.DeleteBed;

public class BedDeletion implements CommandHandler<DeleteBed>{
	
	private final EventStore repository;
	
	public BedDeletion(EventStore repository) {
		this.repository = repository;
	}
	
	@Override
	public void execute(DeleteBed command) {
		Bed bed = repository.pull(Bed.class, command.getId());
		bed.delete();
		repository.push(bed);
	}

}
