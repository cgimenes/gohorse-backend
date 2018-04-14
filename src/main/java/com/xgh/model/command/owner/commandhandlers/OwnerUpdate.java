package com.xgh.model.command.owner.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.owner.Owner;
import com.xgh.model.command.owner.commands.UpdateOwner;

public class OwnerUpdate implements CommandHandler<UpdateOwner>{
	private final EventStore repository;
	
	public OwnerUpdate(EventStore repository) {
		this.repository = repository;
	}
	
	@Override
	public void execute(UpdateOwner command) {
		Owner owner = repository.pull(Owner.class, command.getId());
		owner.update(command.getName(), command.getPhone(),command.getCpf(),command.getBirthDate(),command.getAddress());
		repository.push(owner);
	}
}
