package com.xgh.eventhandlers;

import com.xgh.buildingblocks.event.Event;
import com.xgh.buildingblocks.event.EventHandler;
import com.xgh.exceptions.ProjectionFailedException;
import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.model.command.animal.Animal;
import com.xgh.model.command.animal.events.AnimalWasDeleted;
import com.xgh.model.command.animal.events.AnimalWasRegistered;
import com.xgh.model.command.animal.events.AnimalWasUpdated;
import com.xgh.model.query.animal.AnimalRepository;
import com.xgh.model.query.breed.BreedProjector;
import com.xgh.model.query.owner.Owner;
import com.xgh.model.query.owner.OwnerRepository;
import com.xgh.model.query.specie.SpecieProjector;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnimalProjector implements EventHandler {
    private final PostgresEventStore eventStore;
    private final AnimalRepository repository;
    private final BreedProjector breedProjector;
    private final SpecieProjector specieProjector;
    private final OwnerRepository ownerRepository;

    @Autowired
    public AnimalProjector(PostgresEventStore eventStore, AnimalRepository repository, BreedProjector breedProjector, SpecieProjector specieProjector, OwnerRepository ownerRepository) {
        this.eventStore = eventStore;
        this.repository = repository;
        this.breedProjector = breedProjector;
        this.specieProjector = specieProjector;
        this.ownerRepository = ownerRepository;
    }

    @Override
    public boolean isSubscribedTo(Event<?> event) {
        return event instanceof AnimalWasDeleted
                || event instanceof AnimalWasRegistered
                || event instanceof AnimalWasUpdated;
    }

    @Override
    public void execute(Event<?> event) {
        Animal entity = eventStore.pull(Animal.class, event.getEntityId());

        Optional<Owner> owner = ownerRepository.findById(entity.getOwner().getValue());
        if (!owner.isPresent()) {
            throw new ProjectionFailedException(Owner.class.getSimpleName());
        }

        com.xgh.model.query.breed.Breed breedProjection = breedProjector.execute(entity.getBreed());
        com.xgh.model.query.specie.Specie specieProjection = specieProjector.execute(entity.getSpecie());

        com.xgh.model.query.animal.Animal projection = new com.xgh.model.query.animal.Animal(
                entity.getId().getValue(),
                entity.getName().getValue(),
                owner.get(),
                breedProjection,
                specieProjection,
                entity.getSex().asCharacter(),
                entity.getBirthDate(),
                entity.isCastrated(),
                entity.getWeight(),
                entity.isDeleted()
        );

        repository.save(projection);
    }
}
