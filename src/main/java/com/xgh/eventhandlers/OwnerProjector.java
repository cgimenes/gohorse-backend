package com.xgh.eventhandlers;

import com.xgh.model.query.address.AddressProjector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xgh.buildingblocks.event.Event;
import com.xgh.buildingblocks.event.EventHandler;
import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.model.command.owner.Owner;
import com.xgh.model.command.owner.events.OwnerWasDeleted;
import com.xgh.model.command.owner.events.OwnerWasRegistered;
import com.xgh.model.command.owner.events.OwnerWasUpdated;
import com.xgh.model.query.owner.OwnerRepository;

@Component
public class OwnerProjector implements EventHandler{

    @Autowired
    private PostgresEventStore eventStore;

    @Autowired
    private OwnerRepository repository;

    @Autowired
    private AddressProjector addressProjector;

    @Override
    public boolean isSubscribedTo(Event<?> event) {
        return event instanceof OwnerWasDeleted
            || event instanceof OwnerWasRegistered
            || event instanceof OwnerWasUpdated;
    }

    @Override
    public void execute(Event<?> event) {
        Owner entity = eventStore.pull(Owner.class, event.getEntityId());

        com.xgh.model.query.address.Address addressProjection = addressProjector.execute(entity.getAddress());

        com.xgh.model.query.owner.Owner projection = new com.xgh.model.query.owner.Owner(
                entity.getId().getValue(),
                entity.getName().getValue(),
                entity.getCpf().getValue(),
                entity.getPhone().getValue(),
                entity.getBirthDate().getValue(),
                addressProjection,
                entity.isDeleted());

        repository.save(projection);
    }


}
