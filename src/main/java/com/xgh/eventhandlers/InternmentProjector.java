package com.xgh.eventhandlers;

import java.util.Optional;

import com.xgh.exceptions.ProjectionFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.xgh.buildingblocks.event.Event;
import com.xgh.buildingblocks.event.EventHandler;
import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.model.command.internment.Internment;
import com.xgh.model.command.internment.events.InternmentWasDeleted;
import com.xgh.model.command.internment.events.InternmentWasRegistered;
import com.xgh.model.command.internment.events.InternmentWasUpdated;
import com.xgh.model.query.animal.Animal;
import com.xgh.model.query.animal.AnimalRepository;
import com.xgh.model.query.bed.Bed;
import com.xgh.model.query.bed.BedRepository;
import com.xgh.model.query.internment.InternmentRepository;

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
    public boolean isSubscribedTo(Event<?> event) {
        return event instanceof InternmentWasDeleted
            || event instanceof InternmentWasRegistered
            || event instanceof InternmentWasUpdated;
    }

    @Override
    public void execute(Event<?> event) {
        Internment entity = eventStore.pull(Internment.class, event.getEntityId());

        Optional<Animal> animal = animalRepository.findById(entity.getAnimalId().getValue());
        Optional<Bed> bed = bedRepository.findById(entity.getBedId().getValue());

        if (!animal.isPresent()) {
            throw new ProjectionFailedException(Animal.class.getSimpleName());
        }

        if (!bed.isPresent()) {
            throw new ProjectionFailedException(Bed.class.getSimpleName());
        }

        com.xgh.model.query.internment.Internment internmentProjection =
            new com.xgh.model.query.internment.Internment(
                entity.getId().getValue(),
                bed.get(),
                animal.get(),
                entity.getBusyAt().getValue(),
                entity.getBusyUntil().getValue(),
                entity.isDeleted());

        internmentRepository.save(internmentProjection);
    }
}
