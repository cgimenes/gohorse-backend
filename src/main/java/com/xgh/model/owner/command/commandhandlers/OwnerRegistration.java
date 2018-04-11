package com.xgh.model.owner.command.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.owner.command.Owner;
import com.xgh.model.owner.command.commands.RegisterOwner;

public class OwnerRegistration implements CommandHandler<RegisterOwner> {
	
	private EventStore repository;
	
	public OwnerRegistration(EventStore repository) {
		this.repository = repository;
	}
	
	@Override
	public void execute(RegisterOwner command) {
		Owner owner = new Owner();
		owner.register(command.getId(), command.getName(), command.getPhone(), command.getCpf(), command.getBirthDate(), command.getAddress());
		repository.push(owner);
	}

}
