package com.xgh.model.command.additionalregister.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.additionalregister.AdditionalRegister;
import com.xgh.model.command.additionalregister.commands.DeleteAdditionalRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdditionalRegisterDeletion implements CommandHandler<DeleteAdditionalRegister> {
    private final EventStore eventStore;

    @Autowired
    public AdditionalRegisterDeletion(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    public void execute(DeleteAdditionalRegister command) {
        AdditionalRegister additionalRegister = eventStore.pull(AdditionalRegister.class, command.getId());
        additionalRegister.delete();
        eventStore.push(additionalRegister);
    }
}
