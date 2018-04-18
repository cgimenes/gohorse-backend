package com.xgh.eventhandlers;

import com.xgh.buildingblocks.event.Event;
import com.xgh.buildingblocks.event.EventHandler;
import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.model.command.product.Product;
import com.xgh.model.command.product.events.ProductWasDeleted;
import com.xgh.model.command.product.events.ProductWasRegistered;
import com.xgh.model.command.product.events.ProductWasUpdated;
import com.xgh.model.query.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductProjector implements EventHandler {
    private final PostgresEventStore eventStore;
    private final ProductRepository repository;

    @Autowired
    public ProductProjector(PostgresEventStore eventStore, ProductRepository repository) {
        this.eventStore = eventStore;
        this.repository = repository;
    }

    @Override
    public boolean isSubscribedTo(Event<?> event) {
        return event instanceof ProductWasDeleted
            || event instanceof ProductWasRegistered
            || event instanceof ProductWasUpdated;
    }

    @Override
    public void execute(Event<?> event) {
        Product entity = eventStore.pull(Product.class, event.getEntityId());

        com.xgh.model.query.product.Product projection = new com.xgh.model.query.product.Product(
                entity.getId().getValue(),
                entity.getName().getValue(),
                entity.getPrice(),
                entity.getBrand() != null ? entity.getBrand().getValue() : null,
                entity.getAmount(),
                entity.getSupplierId().getValue(),
                entity.isDeleted());

        repository.save(projection);
    }
}
