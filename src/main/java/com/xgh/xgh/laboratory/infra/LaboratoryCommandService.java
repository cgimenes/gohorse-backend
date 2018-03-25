package com.xgh.xgh.laboratory.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xgh.infra.PostgresEventStore;
import com.xgh.xgh.laboratory.commandmodel.Laboratory;
import com.xgh.xgh.laboratory.commandmodel.LaboratoryId;
import com.xgh.xgh.laboratory.commandmodel.commandhandlers.LaboratoryRegistration;
import com.xgh.xgh.laboratory.commandmodel.commandhandlers.LaboratoryUpdate;
import com.xgh.xgh.laboratory.commandmodel.commands.RegisterLaboratory;
import com.xgh.xgh.laboratory.commandmodel.commands.UpdateLaboratory;

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
