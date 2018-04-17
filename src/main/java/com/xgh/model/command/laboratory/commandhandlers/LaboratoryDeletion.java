package com.xgh.model.command.laboratory.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.laboratory.Laboratory;
import com.xgh.model.command.laboratory.commands.DeleteLaboratory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LaboratoryDeletion implements CommandHandler<DeleteLaboratory>{

    private final EventStore repository;

    @Autowired
    protected LaboratoryDeletion(EventStore repository) {
        this.repository = repository;
    }

    @Override
    public void execute(DeleteLaboratory command) {
        Laboratory laboratory = repository.pull(Laboratory.class, command.getId());
        laboratory.delete();
        repository.push(laboratory);
    }
}
