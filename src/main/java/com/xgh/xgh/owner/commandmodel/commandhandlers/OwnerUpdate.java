package com.xgh.xgh.owner.commandmodel.commandhandlers;

import com.xgh.buildingblocks.CommandHandler;
import com.xgh.buildingblocks.EventStore;
import com.xgh.xgh.owner.commandmodel.Owner;
import com.xgh.xgh.owner.commandmodel.commands.UpdateOwner;

public class OwnerUpdate implements CommandHandler<UpdateOwner>{
	
	private EventStore repository;
	
	public OwnerUpdate(EventStore repository) {
		this.repository = repository;
	}
	
	@Override
	public void execute(UpdateOwner command) {
		Owner owner = repository.pull(Owner.class, command.getId());
		owner.update(command.getName(), command.getPhone(),command.getCpf(),command.getBirthDate());
		repository.push(owner);
	}

}
