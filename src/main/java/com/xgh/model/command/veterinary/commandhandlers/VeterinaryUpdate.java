package com.xgh.model.command.veterinary.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.veterinary.Veterinary;
import com.xgh.model.command.veterinary.commands.UpdateVeterinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VeterinaryUpdate implements CommandHandler<UpdateVeterinary> {

    private final EventStore eventStore;

    @Autowired
    public VeterinaryUpdate(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    public void execute(UpdateVeterinary command) {
        Veterinary veterinary = eventStore.pull(Veterinary.class, command.getId());
        veterinary.update(command.getName(), command.getAddress(), command.getPhone(), command.getCrmv(), command.getEmail(),
                command.getBirthDate());
        eventStore.push(veterinary);
    }

}
