package com.xgh.xgh.laboratory.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.xgh.laboratory.command.commandhandlers.LaboratoryRegistration;
import com.xgh.xgh.laboratory.command.commandhandlers.LaboratoryUpdate;
import com.xgh.xgh.laboratory.command.commands.RegisterLaboratory;
import com.xgh.xgh.laboratory.command.commands.UpdateLaboratory;

@Service
public class LaboratoryCommandService {
    @Autowired
    private PostgresEventStore<Laboratory, LaboratoryId> eventStore;

    public LaboratoryId registerLaboratory(RegisterLaboratory command) {
        LaboratoryRegistration handler = new LaboratoryRegistration(eventStore);
        handler.execute(command);
        return command.getId();
    }

	public void updateLaboratory(UpdateLaboratory command) {
        LaboratoryUpdate handler = new LaboratoryUpdate(eventStore);
        handler.execute(command);
	}
}
