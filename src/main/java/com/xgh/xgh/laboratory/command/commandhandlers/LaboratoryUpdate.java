package com.xgh.xgh.laboratory.command.commandhandlers;

import com.xgh.buildingblocks.CommandHandler;
import com.xgh.xgh.laboratory.command.Laboratory;
import com.xgh.xgh.laboratory.command.LaboratoryCommandRepository;
import com.xgh.xgh.laboratory.command.commands.UpdateLaboratory;

public class LaboratoryUpdate extends CommandHandler<UpdateLaboratory>{

    private LaboratoryCommandRepository repository;

    public LaboratoryUpdate(LaboratoryCommandRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(UpdateLaboratory command) {
        Laboratory laboratory = repository.pull(command.getId());
        laboratory.update(command.getCompanyName(), command.getPhone());
        repository.push(laboratory);
    }
}
