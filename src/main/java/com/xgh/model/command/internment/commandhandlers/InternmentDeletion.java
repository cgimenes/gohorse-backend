package com.xgh.model.command.internment.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.internment.Internment;
import com.xgh.model.command.internment.commands.DeleteInternment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InternmentDeletion implements CommandHandler<DeleteInternment> {

    private final EventStore repository;

    @Autowired
    public InternmentDeletion(EventStore repository) {
        this.repository = repository;
    }

    @Override
    public void execute(DeleteInternment command) {
        Internment internment = repository.pull(Internment.class, command.getId());
        internment.delete();
        repository.push(internment);
    }

}