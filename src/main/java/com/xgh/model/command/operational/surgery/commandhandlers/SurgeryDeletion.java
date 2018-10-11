package com.xgh.model.command.operational.surgery.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.operational.surgery.Surgery;
import com.xgh.model.command.operational.surgery.commands.DeleteSurgery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SurgeryDeletion implements CommandHandler<DeleteSurgery> {
    private final EventStore repository;

    @Autowired
    public SurgeryDeletion(EventStore repository) {
        this.repository = repository;
    }

    @Override
    public void execute(DeleteSurgery command) {
    	Surgery surgery = repository.pull(Surgery.class, command.getId());
        surgery.delete();
        repository.push(surgery);
    }
}
