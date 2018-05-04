package com.xgh.model.command.bed.commandhandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.bed.Bed;
import com.xgh.model.command.bed.commands.UpdateBed;

@Component
public class BedUpdate implements CommandHandler<UpdateBed>{

	private final EventStore eventStore;
	
	@Autowired
	public BedUpdate(EventStore eventStore) {
		this.eventStore = eventStore;
	}
	
	@Override
	public void execute(UpdateBed command) {
		Bed bed = eventStore.pull(Bed.class , command.getId());
		bed.update(command.getCode(), command.isBusy());
		eventStore.push(bed);
		
	}
}
