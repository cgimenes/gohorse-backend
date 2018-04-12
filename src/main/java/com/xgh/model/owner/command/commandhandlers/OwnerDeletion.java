package com.xgh.model.owner.command.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.owner.command.Owner;
import com.xgh.model.owner.command.commands.DeleteOwner;

public class OwnerDeletion implements CommandHandler<DeleteOwner>{
	
	private EventStore repository;
	
	public OwnerDeletion(EventStore repository) {
		this.repository = repository;
	}
	
	@Override
	public void execute(DeleteOwner command) {
		Owner owner = repository.pull(Owner.class, command.getId());
		owner.delete();
		repository.push(owner);
	}

}
