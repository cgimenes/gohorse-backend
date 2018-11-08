package com.xgh.eventhandlers.operational;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.buildingblocks.event.Event;
import com.xgh.buildingblocks.event.EventHandler;
import com.xgh.model.command.operational.owner.Owner;
import com.xgh.model.command.operational.owner.events.OwnerWasDeleted;
import com.xgh.model.command.operational.owner.events.OwnerWasRegistered;
import com.xgh.model.command.operational.owner.events.OwnerWasUpdated;
import com.xgh.model.query.operational.address.AddressProjector;
import com.xgh.model.query.operational.owner.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OwnerProjector implements EventHandler {
    private final EventStore eventStore;
    private final OwnerRepository repository;
    private final AddressProjector addressProjector;

    @Autowired
    public OwnerProjector(EventStore eventStore, AddressProjector addressProjector, OwnerRepository repository) {
        this.eventStore = eventStore;
        this.addressProjector = addressProjector;
        this.repository = repository;
    }

    @Override
    public boolean isSubscribedTo(Event event) {
        return event instanceof OwnerWasDeleted
                || event instanceof OwnerWasRegistered
                || event instanceof OwnerWasUpdated;
    }

    @Override
    public void execute(Event event) {
        Owner entity = eventStore.pull(Owner.class, ((EntityEvent<?>) event).getEntityId());

        com.xgh.model.query.operational.address.Address addressProjection = addressProjector.execute(entity.getAddress());

        com.xgh.model.query.operational.owner.Owner projection = new com.xgh.model.query.operational.owner.Owner(
                entity.getId().getValue(),
                entity.getName().getValue(),
                entity.getDocument().getValue(),
                entity.getPhone().getValue(),
                entity.getBirthDate(),
                addressProjection,
                entity.isDeleted(),
                entity.getEmail().toString());

        repository.save(projection);
    }
}
