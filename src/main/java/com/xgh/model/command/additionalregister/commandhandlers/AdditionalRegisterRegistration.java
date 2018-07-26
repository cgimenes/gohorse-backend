package com.xgh.model.command.additionalregister.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.additionalregister.AdditionalRegister;
import com.xgh.model.command.additionalregister.commands.RegisterAdditionalRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdditionalRegisterRegistration implements CommandHandler<RegisterAdditionalRegister> {
    private final EventStore eventStore;

    @Autowired
    public AdditionalRegisterRegistration(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    public void execute(RegisterAdditionalRegister command) {
        AdditionalRegister additionalRegister = new AdditionalRegister();
        additionalRegister.register(
                command.getId(),
                command.getType(),
                command.getValue());
        eventStore.push(additionalRegister);
    }
}
