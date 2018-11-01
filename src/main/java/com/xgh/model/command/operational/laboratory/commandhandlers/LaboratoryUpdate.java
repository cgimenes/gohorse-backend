package com.xgh.model.command.operational.laboratory.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.operational.laboratory.Laboratory;
import com.xgh.model.command.operational.laboratory.commands.UpdateLaboratory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LaboratoryUpdate implements CommandHandler<UpdateLaboratory> {

    private final EventStore eventStore;

    @Autowired
    public LaboratoryUpdate(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    public void execute(UpdateLaboratory command) {
        Laboratory laboratory = eventStore.pull(Laboratory.class, command.getId());
        laboratory.update(command.getCompanyName(), command.getPhone(), command.getAddress());
        eventStore.push(laboratory);
    }
}
