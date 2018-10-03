package com.xgh.eventhandlers.operational;

import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.buildingblocks.event.Event;
import com.xgh.buildingblocks.event.EventHandler;
import com.xgh.exceptions.ProjectionFailedException;
import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.model.command.operational.internment.Internment;
import com.xgh.model.command.operational.internment.events.InternmentWasDeleted;
import com.xgh.model.command.operational.internment.events.InternmentWasRegistered;
import com.xgh.model.command.operational.internment.events.InternmentWasUpdated;
import com.xgh.model.query.operational.animal.Animal;
import com.xgh.model.query.operational.animal.AnimalRepository;
import com.xgh.model.query.operational.bed.Bed;
import com.xgh.model.query.operational.bed.BedRepository;
import com.xgh.model.query.operational.internment.InternmentRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InternmentProjector implements EventHandler {

    private final PostgresEventStore eventStore;
    private final InternmentRepository internmentRepository;
    private final AnimalRepository animalRepository;
    private final BedRepository bedRepository;

    @Autowired
    public InternmentProjector(PostgresEventStore eventStore, InternmentRepository internmentRepository,
                               AnimalRepository animalRepository, BedRepository bedRepository) {
        this.eventStore = eventStore;
        this.internmentRepository = internmentRepository;
        this.animalRepository = animalRepository;
        this.bedRepository = bedRepository;
    }

    @Override
    public boolean isSubscribedTo(Event event) {
        return event instanceof InternmentWasDeleted
                || event instanceof InternmentWasRegistered
                || event instanceof InternmentWasUpdated;
    }

    @Override
    public void execute(Event event) {
        Internment entity = eventStore.pull(Internment.class, ((EntityEvent<?>) event).getEntityId());

        Optional<Animal> animal = animalRepository.findById(entity.getAnimalId().getValue());
        Optional<Bed> bed = bedRepository.findById(entity.getBedId().getValue());

        if (!animal.isPresent()) {
            throw new ProjectionFailedException(Animal.class.getSimpleName());
        }

        if (!bed.isPresent()) {
            throw new ProjectionFailedException(Bed.class.getSimpleName());
        }

        com.xgh.model.query.operational.internment.Internment internmentProjection =
                new com.xgh.model.query.operational.internment.Internment(
                        entity.getId().getValue(),
                        bed.get(),
                        animal.get(),
                        entity.getBusyAt(),
                        entity.getBusyUntil(),
                        entity.isDeleted());

        internmentRepository.save(internmentProjection);
    }
}
