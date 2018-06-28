package com.xgh.eventhandlers;

import com.xgh.buildingblocks.event.Event;
import com.xgh.buildingblocks.event.EventHandler;
import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.model.command.supplier.Supplier;
import com.xgh.model.command.supplier.events.SupplierWasDeleted;
import com.xgh.model.command.supplier.events.SupplierWasRegistered;
import com.xgh.model.command.supplier.events.SupplierWasUpdated;
import com.xgh.model.query.address.AddressProjector;
import com.xgh.model.query.supplier.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SupplierProjector implements EventHandler {
    private AddressProjector addressProjector;
    private PostgresEventStore eventStore;
    private SupplierRepository repository;

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

        com.xgh.model.query.address.Address addressProjection = addressProjector.execute(entity.getAddress());

        com.xgh.model.query.supplier.Supplier projection = new com.xgh.model.query.supplier.Supplier(
                entity.getId().getValue(),
                entity.getName().getValue(),
                entity.getPhone().getValue(),
                entity.getDocument(),
                addressProjection,
                entity.isDeleted());

        repository.save(projection);
    }
}
