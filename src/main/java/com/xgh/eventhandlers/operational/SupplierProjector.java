package com.xgh.eventhandlers.operational;

import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.buildingblocks.event.Event;
import com.xgh.buildingblocks.event.EventHandler;
import com.xgh.exceptions.ProjectionFailedException;
import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.model.command.operational.supplier.Supplier;
import com.xgh.model.command.operational.supplier.events.SupplierWasDeleted;
import com.xgh.model.command.operational.supplier.events.SupplierWasRegistered;
import com.xgh.model.command.operational.supplier.events.SupplierWasUpdated;
import com.xgh.model.query.operational.address.AddressProjector;
import com.xgh.model.query.operational.enumerator.Enumerator;
import com.xgh.model.query.operational.enumerator.EnumeratorRepository;
import com.xgh.model.query.operational.supplier.SupplierRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SupplierProjector implements EventHandler {
    private final AddressProjector addressProjector;
    private final PostgresEventStore eventStore;
    private final SupplierRepository repository;
    private final EnumeratorRepository distributionTypeRepository;

    @Autowired
    public SupplierProjector(
            AddressProjector addressProjector,
            PostgresEventStore eventStore,
            SupplierRepository repository,
            EnumeratorRepository distributionTypeRepository
    ) {
        this.addressProjector = addressProjector;
        this.eventStore = eventStore;
        this.repository = repository;
        this.distributionTypeRepository = distributionTypeRepository;
    }

    @Override
    public boolean isSubscribedTo(Event event) {
        return event instanceof SupplierWasDeleted
                || event instanceof SupplierWasRegistered
                || event instanceof SupplierWasUpdated;
    }

    @Override
    public void execute(Event event) {
        Supplier entity = eventStore.pull(Supplier.class, ((EntityEvent<?>) event).getEntityId());
        
        Optional<Enumerator> distributionType = distributionTypeRepository.findById(entity.getDistributionType().getValue());
        if(!distributionType.isPresent()) {
        	throw new ProjectionFailedException(Enumerator.class.getSimpleName());
        }

        com.xgh.model.query.operational.address.Address addressProjection = addressProjector.execute(entity.getAddress());

        com.xgh.model.query.operational.supplier.Supplier projection = new com.xgh.model.query.operational.supplier.Supplier(
                entity.getId().getValue(),
                entity.getName().getValue(),
                entity.getPhone().getValue(),
                distributionType.get(),
                entity.getDocument(),
                addressProjection,
                entity.isDeleted());

        repository.save(projection);
    }
}
