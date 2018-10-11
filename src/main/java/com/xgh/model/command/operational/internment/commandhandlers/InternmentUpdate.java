package com.xgh.model.command.operational.internment.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.exceptions.EntityNotFoundException;
import com.xgh.model.command.operational.animal.Animal;
import com.xgh.model.command.operational.bed.Bed;
import com.xgh.model.command.operational.internment.Internment;
import com.xgh.model.command.operational.internment.commands.UpdateInternment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InternmentUpdate implements CommandHandler<UpdateInternment> {

    private final EventStore eventStore;

    @Autowired
    public InternmentUpdate(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    public void execute(UpdateInternment command) {
        if (!eventStore.entityExists(Bed.class, command.getBedId())) {
            throw new EntityNotFoundException(Bed.class.getSimpleName(), command.getAnimalId().getValue());
        }

        if (!eventStore.entityExists(Animal.class, command.getAnimalId())) {
            throw new EntityNotFoundException(Animal.class.getSimpleName(), command.getAnimalId().getValue());
        }

        Internment internment = eventStore.pull(Internment.class, command.getId());
        internment.update(command.getBedId(), command.getAnimalId(), command.getBusyAt(), command.getBusyUntil());
        eventStore.push(internment);
    }

}
