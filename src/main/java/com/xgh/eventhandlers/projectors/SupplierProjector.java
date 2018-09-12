package com.xgh.eventhandlers.projectors;

import com.xgh.buildingblocks.event.Event;
import com.xgh.buildingblocks.event.EventHandler;
import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.model.command.operational.supplier.Supplier;
import com.xgh.model.command.operational.supplier.events.SupplierWasDeleted;
import com.xgh.model.command.operational.supplier.events.SupplierWasRegistered;
import com.xgh.model.command.operational.supplier.events.SupplierWasUpdated;
import com.xgh.model.query.operational.address.AddressProjector;
import com.xgh.model.query.operational.supplier.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SupplierProjector implements EventHandler {
    private final AddressProjector addressProjector;
    private final PostgresEventStore eventStore;
    private final SupplierRepository repository;

    @Autowired
    public SupplierProjector(
            AddressProjector addressProjector,
            PostgresEventStore eventStore,
            SupplierRepository repository
    ) {
        this.addressProjector = addressProjector;
        this.eventStore = eventStore;
        this.repository = repository;
    }

    @Override
    public boolean isSubscribedTo(Event<?> event) {
        return event instanceof SupplierWasDeleted
                || event instanceof SupplierWasRegistered
                || event instanceof SupplierWasUpdated;
    }

    @Override
    public void execute(Event<?> event) {
        Supplier entity = eventStore.pull(Supplier.class, event.getEntityId());

        com.xgh.model.query.operational.address.Address addressProjection = addressProjector.execute(entity.getAddress());

        com.xgh.model.query.operational.supplier.Supplier projection = new com.xgh.model.query.operational.supplier.Supplier(
                entity.getId().getValue(),
                entity.getName().getValue(),
                entity.getPhone().getValue(),
                entity.getDocument(),
                addressProjection,
                entity.isDeleted());

        repository.save(projection);
    }
}
