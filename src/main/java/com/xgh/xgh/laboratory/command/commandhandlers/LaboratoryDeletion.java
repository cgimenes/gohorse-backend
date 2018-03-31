package com.xgh.xgh.laboratory.command.commandhandlers;

import com.xgh.buildingblocks.CommandHandler;
import com.xgh.buildingblocks.EventStore;
import com.xgh.xgh.laboratory.command.Laboratory;
import com.xgh.xgh.laboratory.command.commands.DeleteLaboratory;

public class LaboratoryDeletion implements CommandHandler<DeleteLaboratory>{

    private EventStore repository;

    public LaboratoryDeletion(EventStore repository) {
        this.repository = repository;
    }

    @Override
    public void execute(DeleteLaboratory command) {
        Laboratory laboratory = repository.pull(Laboratory.class, command.getId());
        laboratory.delete();
        repository.push(laboratory);
    }
}
