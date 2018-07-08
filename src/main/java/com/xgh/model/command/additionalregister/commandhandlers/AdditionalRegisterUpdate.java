package com.xgh.model.command.additionalregister.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.additionalregister.AdditionalRegister;
import com.xgh.model.command.additionalregister.commands.UpdateAdditionalRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdditionalRegisterUpdate implements CommandHandler<UpdateAdditionalRegister> {
    private final EventStore eventStore;

    @Autowired
    public AdditionalRegisterUpdate(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    public void execute(UpdateAdditionalRegister command) {
        AdditionalRegister additionalRegister = eventStore.pull(AdditionalRegister.class, command.getId());
        additionalRegister.update(
                command.getRegisterType(),
                command.getValue());
        eventStore.push(additionalRegister);
    }
}
