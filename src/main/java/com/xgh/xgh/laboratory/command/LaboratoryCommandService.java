package com.xgh.xgh.laboratory.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xgh.xgh.laboratory.command.commandhandlers.LaboratoryRegistration;
import com.xgh.xgh.laboratory.command.commandhandlers.LaboratoryUpdate;
import com.xgh.xgh.laboratory.command.commands.RegisterLaboratory;
import com.xgh.xgh.laboratory.command.commands.UpdateLaboratory;

@Component
public class LaboratoryCommandService {
    @Autowired
    private LaboratoryCommandRepository repository;

    public LaboratoryId registerLaboratory(RegisterLaboratory command) {
        LaboratoryRegistration handler = new LaboratoryRegistration(repository);
        handler.execute(command);
        return command.getId();
    }

	public void updateLaboratory(UpdateLaboratory command) {
        LaboratoryUpdate handler = new LaboratoryUpdate(repository);
        handler.execute(command);
	}
}
