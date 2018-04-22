package com.xgh.model.command.veterinary.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.veterinary.Veterinary;
import com.xgh.model.command.veterinary.commands.RegisterVeterinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VeterinaryRegistration implements CommandHandler<RegisterVeterinary> {

    private final EventStore eventStore;

    @Autowired
    public VeterinaryRegistration(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    public void execute(RegisterVeterinary command) {
        Veterinary veterinary = new Veterinary();
        veterinary.register(command.getId(), command.getName(), command.getAddress(), command.getPhone(),
                command.getCrmv(), command.getEmail(), command.getBirthDate());
        eventStore.push(veterinary);
    }
}
