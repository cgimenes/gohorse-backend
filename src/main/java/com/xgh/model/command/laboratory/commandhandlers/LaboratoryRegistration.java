package com.xgh.model.command.laboratory.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.laboratory.Laboratory;
import com.xgh.model.command.laboratory.commands.RegisterLaboratory;

public class LaboratoryRegistration implements CommandHandler<RegisterLaboratory>{

    private final EventStore repository;

    public LaboratoryRegistration(EventStore repository) {
        this.repository = repository;
    }

    @Override
    public void execute(RegisterLaboratory command) {
        Laboratory laboratory = new Laboratory();
        laboratory.register(command.getId(), command.getCompanyName(), command.getPhone(), command.getAddress());
        repository.push(laboratory);
	}
}