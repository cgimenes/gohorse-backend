package com.xgh.xgh.command.laboratory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xgh.xgh.command.laboratory.commandhandlers.LaboratoryRegistration;
import com.xgh.xgh.command.laboratory.commandhandlers.LaboratoryUpdate;
import com.xgh.xgh.command.laboratory.commands.RegisterLaboratory;
import com.xgh.xgh.command.laboratory.commands.UpdateLaboratory;

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
