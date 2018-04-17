package com.xgh.model.command.owner.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.owner.Owner;
import com.xgh.model.command.owner.commands.RegisterOwner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OwnerRegistration implements CommandHandler<RegisterOwner> {
    private final EventStore repository;

    @Autowired
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
