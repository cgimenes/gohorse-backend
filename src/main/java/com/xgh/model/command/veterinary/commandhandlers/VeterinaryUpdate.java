package com.xgh.model.command.veterinary.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.veterinary.Veterinary;
import com.xgh.model.command.veterinary.commands.UpdateVeterinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VeterinaryUpdate implements CommandHandler<UpdateVeterinary> {

    private final EventStore repository;

    @Autowired
    public VeterinaryUpdate(EventStore repository) {
        this.repository = repository;
    }

    @Override
    public void execute(UpdateVeterinary command) {
        Veterinary veterinary = repository.pull(Veterinary.class, command.getId());
        veterinary.update(command.getName(), command.getAddress(), command.getPhone(), command.getCrmv(), command.getEmail(),
                command.getBirthDate());
        repository.push(veterinary);
    }

}
