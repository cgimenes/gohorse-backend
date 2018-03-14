package com.xgh.xgh.command.laboratory.commandhandlers;

import com.xgh.buildingblocks.CommandHandler;
import com.xgh.xgh.command.laboratory.Laboratory;
import com.xgh.xgh.command.laboratory.LaboratoryCommandRepository;
import com.xgh.xgh.command.laboratory.commands.UpdateLaboratory;

public class LaboratoryUpdate extends CommandHandler<UpdateLaboratory>{

    private LaboratoryCommandRepository repository;

    public LaboratoryUpdate(LaboratoryCommandRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(UpdateLaboratory command) {
        Laboratory laboratory = repository.findById(command.getId());
        laboratory.update(command.getName(), command.getPhone());
        repository.update(laboratory);
    }
}
