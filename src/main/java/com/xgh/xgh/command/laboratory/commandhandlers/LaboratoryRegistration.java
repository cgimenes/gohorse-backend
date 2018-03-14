package com.xgh.xgh.command.laboratory.commandhandlers;

import com.xgh.buildingblocks.CommandHandler;
import com.xgh.xgh.command.laboratory.Laboratory;
import com.xgh.xgh.command.laboratory.LaboratoryCommandRepository;
import com.xgh.xgh.command.laboratory.commands.RegisterLaboratory;

public class LaboratoryRegistration extends CommandHandler<RegisterLaboratory>{

    private LaboratoryCommandRepository repository;

    public LaboratoryRegistration(LaboratoryCommandRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(RegisterLaboratory command) {
        Laboratory laboratory = Laboratory.register(
                command.getId(),
                command.getName(),
                command.getPhone());

        repository.insert(laboratory);
    }
}
