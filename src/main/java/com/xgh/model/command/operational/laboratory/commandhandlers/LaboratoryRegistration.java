package com.xgh.model.command.operational.laboratory.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.operational.laboratory.Laboratory;
import com.xgh.model.command.operational.laboratory.commands.RegisterLaboratory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LaboratoryRegistration implements CommandHandler<RegisterLaboratory> {

    private final EventStore eventStore;

    @Autowired
    public LaboratoryRegistration(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    public void execute(RegisterLaboratory command) {
        Laboratory laboratory = new Laboratory();
        laboratory.register(command.getId(), command.getCompanyName(), command.getPhone(), command.getAddress());
        eventStore.push(laboratory);
    }
}
