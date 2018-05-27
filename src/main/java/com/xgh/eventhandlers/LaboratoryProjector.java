package com.xgh.eventhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.event.Event;
import com.xgh.buildingblocks.event.EventHandler;
import com.xgh.model.command.laboratory.Laboratory;
import com.xgh.model.command.laboratory.events.LaboratoryWasDeleted;
import com.xgh.model.command.laboratory.events.LaboratoryWasRegistered;
import com.xgh.model.command.laboratory.events.LaboratoryWasUpdated;
import com.xgh.model.query.address.Address;
import com.xgh.model.query.address.AddressProjector;
import com.xgh.model.query.laboratory.LaboratoryRepository;
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
    public boolean isSubscribedTo(Event<?> event) {
        return event instanceof LaboratoryWasDeleted
                || event instanceof LaboratoryWasRegistered
                || event instanceof LaboratoryWasUpdated;
    }

    @Override
    public void execute(Event<?> event) {
        Laboratory entity = eventStore.pull(Laboratory.class, event.getEntityId());

        Address addressProjection = addressProjector.execute(entity.getAddress());

        com.xgh.model.query.laboratory.Laboratory laboratoryProjection = new com.xgh.model.query.laboratory.Laboratory(
                entity.getId().getValue(),
                entity.getCompanyName().getValue(),
                entity.getPhone().getValue(),
                addressProjection,
                entity.isDeleted());

        laboratoryRepository.save(laboratoryProjection);
    }
}
