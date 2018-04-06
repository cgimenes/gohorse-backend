package com.xgh.xgh.laboratory.command.commandhandlers;

import com.xgh.buildingblocks.CommandHandler;
import com.xgh.buildingblocks.EventStore;
import com.xgh.xgh.laboratory.command.Laboratory;
import com.xgh.xgh.laboratory.command.commands.UpdateLaboratory;

public class LaboratoryUpdate implements CommandHandler<UpdateLaboratory>{

    private EventStore repository;

    public LaboratoryUpdate(EventStore repository) {
        this.repository = repository;
    }

    @Override
    public void execute(UpdateLaboratory command) {
        Laboratory laboratory = repository.pull(Laboratory.class, command.getId());
        laboratory.update(command.getCompanyName(), command.getPhone());
        repository.push(laboratory);
    }
}