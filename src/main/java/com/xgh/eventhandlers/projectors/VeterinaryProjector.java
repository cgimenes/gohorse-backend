package com.xgh.eventhandlers.projectors;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.event.Event;
import com.xgh.buildingblocks.event.EventHandler;
import com.xgh.model.command.operational.veterinary.Veterinary;
import com.xgh.model.command.operational.veterinary.events.VeterinaryWasDeleted;
import com.xgh.model.command.operational.veterinary.events.VeterinaryWasRegistered;
import com.xgh.model.command.operational.veterinary.events.VeterinaryWasUpdated;
import com.xgh.model.query.operational.address.Address;
import com.xgh.model.query.operational.address.AddressProjector;
import com.xgh.model.query.operational.veterinary.VeterinaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VeterinaryProjector implements EventHandler {
    private final EventStore eventStore;
    private final VeterinaryRepository repository;
    private final AddressProjector addressProjector;

    @Autowired
    public VeterinaryProjector(VeterinaryRepository repository, EventStore eventStore, AddressProjector addressProjector) {
        this.repository = repository;
        this.eventStore = eventStore;
        this.addressProjector = addressProjector;
    }

    @Override
    public boolean isSubscribedTo(Event<?> event) {
        return event instanceof VeterinaryWasDeleted
                || event instanceof VeterinaryWasRegistered
                || event instanceof VeterinaryWasUpdated;
    }

    @Override
    public void execute(Event<?> event) {
        Veterinary entity = eventStore.pull(Veterinary.class, event.getEntityId());

        Address addressProjection = addressProjector.execute(entity.getAddress());

        com.xgh.model.query.operational.veterinary.Veterinary projection = new com.xgh.model.query.operational.veterinary.Veterinary(
                entity.getId().getValue(),
                entity.getName().getValue(),
                addressProjection,
                entity.getPhone().getValue(),
                entity.getCrmv().getValue(),
                entity.getEmail() != null ? entity.getEmail().getValue() : null,
                entity.getBirthDate(),
                entity.isDeleted());

        repository.save(projection);
    }
}
