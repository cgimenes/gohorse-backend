package com.xdg.xdg.laboratory.commandhandlers;

import com.xdg.buildingblocks.CommandHandler;
import com.xdg.xdg.laboratory.Laboratory;
import com.xdg.xdg.laboratory.LaboratoryRepository;
import com.xdg.xdg.laboratory.commands.RegisterLaboratory;

public class LaboratoryRegistration extends CommandHandler<RegisterLaboratory>{

    private LaboratoryRepository repository;

    public LaboratoryRegistration(LaboratoryRepository repository) {
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
