package com.xgh.model.command.internment.commandhandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.exceptions.EntityNotFoundException;
import com.xgh.model.command.animal.Animal;
import com.xgh.model.command.bed.Bed;
import com.xgh.model.command.internment.Internment;
import com.xgh.model.command.internment.commands.UpdateInternment;

@Component
public class InternmentUpdate implements CommandHandler<UpdateInternment> {

    private final EventStore eventStore;

    @Autowired
    public InternmentUpdate(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    public void execute(UpdateInternment command) {
        Internment internment = eventStore.pull(Internment.class, command.getId());

        if (!eventStore.entityExists(Bed.class, command.getBedId())) {
            throw new EntityNotFoundException(Bed.class.getSimpleName());
        }

        if (!eventStore.entityExists(Animal.class, command.getAnimalId())) {
            throw new EntityNotFoundException(Animal.class.getSimpleName());
        }

        internment.update(command.getBedId(), command.getAnimalId(), command.getBusyAt(), command.getBusyUntil());
        eventStore.push(internment);
    }

}
