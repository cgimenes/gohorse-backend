package com.xgh.xgh.laboratory.commandmodel.commandhandlers;

import com.xgh.buildingblocks.CommandHandler;
import com.xgh.buildingblocks.EventStore;
import com.xgh.xgh.laboratory.commandmodel.Laboratory;
import com.xgh.xgh.laboratory.commandmodel.commands.RegisterLaboratory;

public class LaboratoryRegistration implements CommandHandler<RegisterLaboratory>{

    private EventStore repository;

    public LaboratoryRegistration(EventStore repository) {
        this.repository = repository;
    }

    @Override
    public void execute(RegisterLaboratory command) {
        Laboratory laboratory = new Laboratory();
        laboratory.register(command.getId(), command.getCompanyName(), command.getPhone());
		repository.push(laboratory);
	}
}
