package com.xgh.model.command.owner.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.owner.Owner;
import com.xgh.model.command.owner.commands.DeleteOwner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OwnerDeletion implements CommandHandler<DeleteOwner>{
    private final EventStore repository;

    @Autowired
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
