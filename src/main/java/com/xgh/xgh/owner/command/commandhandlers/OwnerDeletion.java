package com.xgh.xgh.owner.command.commandhandlers;

import com.xgh.buildingblocks.CommandHandler;
import com.xgh.buildingblocks.EventStore;
import com.xgh.xgh.owner.command.Owner;
import com.xgh.xgh.owner.command.commands.DeleteOwner;

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
