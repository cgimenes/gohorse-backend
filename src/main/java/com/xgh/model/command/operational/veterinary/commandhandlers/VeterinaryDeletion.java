package com.xgh.model.command.operational.veterinary.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.operational.veterinary.Veterinary;
import com.xgh.model.command.operational.veterinary.commands.DeleteVeterinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VeterinaryDeletion implements CommandHandler<DeleteVeterinary> {

    private final EventStore eventStore;

    @Autowired
    public VeterinaryDeletion(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    public void execute(DeleteVeterinary command) {
        Veterinary veterinary = eventStore.pull(Veterinary.class, command.getId());
        veterinary.delete();
        eventStore.push(veterinary);
    }
}
