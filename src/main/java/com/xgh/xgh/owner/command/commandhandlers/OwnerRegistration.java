package com.xgh.xgh.owner.command.commandhandlers;

import com.xgh.buildingblocks.CommandHandler;
import com.xgh.buildingblocks.EventStore;
import com.xgh.xgh.owner.command.Owner;
import com.xgh.xgh.owner.command.commands.RegisterOwner;

public class OwnerRegistration implements CommandHandler<RegisterOwner> {
	
	private EventStore repository;
	
	public OwnerRegistration(EventStore repository) {
		this.repository = repository;
	}
	
	@Override
	public void execute(RegisterOwner command) {
		Owner owner = new Owner();
		owner.register(command.getId(), command.getName(), command.getPhone(), command.getCpf(), command.getBirthDate());
		repository.push(owner);
	}

}
