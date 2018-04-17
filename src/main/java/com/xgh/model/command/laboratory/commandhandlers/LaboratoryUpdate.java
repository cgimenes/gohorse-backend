package com.xgh.model.command.laboratory.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.laboratory.Laboratory;
import com.xgh.model.command.laboratory.commands.UpdateLaboratory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LaboratoryUpdate implements CommandHandler<UpdateLaboratory>{

    private final EventStore repository;

    @Autowired
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
