package com.xgh.eventhandlers.operational;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.buildingblocks.event.Event;
import com.xgh.buildingblocks.event.EventHandler;
import com.xgh.model.command.operational.laboratory.Laboratory;
import com.xgh.model.command.operational.laboratory.events.LaboratoryWasDeleted;
import com.xgh.model.command.operational.laboratory.events.LaboratoryWasRegistered;
import com.xgh.model.command.operational.laboratory.events.LaboratoryWasUpdated;
import com.xgh.model.query.operational.address.Address;
import com.xgh.model.query.operational.address.AddressProjector;
import com.xgh.model.query.operational.laboratory.LaboratoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LaboratoryProjector implements EventHandler {
    private final EventStore eventStore;
    private final LaboratoryRepository laboratoryRepository;
    private final AddressProjector addressProjector;

    @Autowired
    public LaboratoryProjector(EventStore eventStore, AddressProjector addressProjector, LaboratoryRepository laboratoryRepository) {
        this.eventStore = eventStore;
        this.addressProjector = addressProjector;
        this.laboratoryRepository = laboratoryRepository;
    }

    @Override
    public boolean isSubscribedTo(Event event) {
        return event instanceof LaboratoryWasDeleted
                || event instanceof LaboratoryWasRegistered
                || event instanceof LaboratoryWasUpdated;
    }

    @Override
    public void execute(Event event) {
        Laboratory entity = eventStore.pull(Laboratory.class, ((EntityEvent<?>) event).getEntityId());

        Address addressProjection = addressProjector.execute(entity.getAddress());

        com.xgh.model.query.operational.laboratory.Laboratory laboratoryProjection = new com.xgh.model.query.operational.laboratory.Laboratory(
                entity.getId().getValue(),
                entity.getCompanyName().getValue(),
                entity.getPhone().getValue(),
                addressProjection,
                entity.isDeleted());

        laboratoryRepository.save(laboratoryProjection);
    }
}
