package com.xgh.model.laboratory.command.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.laboratory.command.Laboratory;
import com.xgh.model.laboratory.command.commands.UpdateLaboratory;

public class LaboratoryUpdate implements CommandHandler<UpdateLaboratory>{

    private EventStore repository;

    public LaboratoryUpdate(EventStore repository) {
        this.repository = repository;
    }

    @Override
    public void execute(UpdateLaboratory command) {
        Laboratory laboratory = repository.pull(Laboratory.class, command.getId());
        laboratory.update(command.getCompanyName(), command.getPhone(), command.getAddress());
        repository.push(laboratory);
    }
}
