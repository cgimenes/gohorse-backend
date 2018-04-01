package com.xgh.xgh.owner.commandmodel.commandhandlers;

import com.xgh.buildingblocks.CommandHandler;
import com.xgh.buildingblocks.EventStore;
import com.xgh.xgh.owner.commandmodel.Owner;
import com.xgh.xgh.owner.commandmodel.commands.RegisterOwner;

public class OwnerRegistration implements CommandHandler<RegisterOwner> {
	
	private EventStore repository;
	
	public OwnerRegistration(EventStore repository) {
		this.repository = repository;
	}
	
	@Override
	public void execute(RegisterOwner command) {
		Owner owner = new Owner();
		owner.register(command.getId(), command.getName(), command.getPhone(), command.getCpf());
		repository.push(owner);
	}

}
