package com.xgh.eventhandlers.projectors;

import com.xgh.buildingblocks.event.Event;
import com.xgh.buildingblocks.event.EventHandler;
import com.xgh.exceptions.ProjectionFailedException;
import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.model.command.operational.product.Product;
import com.xgh.model.command.operational.product.events.ProductWasDeleted;
import com.xgh.model.command.operational.product.events.ProductWasRegistered;
import com.xgh.model.command.operational.product.events.ProductWasUpdated;
import com.xgh.model.query.operational.product.ProductRepository;
import com.xgh.model.query.operational.supplier.Supplier;
import com.xgh.model.query.operational.supplier.SupplierRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductProjector implements EventHandler {
    private final PostgresEventStore eventStore;
    private final ProductRepository repository;
    private final SupplierRepository supplierRepository;

    @Autowired
    public ProductProjector(PostgresEventStore eventStore, ProductRepository repository, SupplierRepository supplierRepository) {
        this.eventStore = eventStore;
        this.repository = repository;
        this.supplierRepository = supplierRepository;
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

        Optional<Supplier> supplier = supplierRepository.findById(entity.getSupplierId().getValue());

        if (!supplier.isPresent()) {
            throw new ProjectionFailedException(Supplier.class.getSimpleName());
        }

        com.xgh.model.query.operational.product.Product projection = new com.xgh.model.query.operational.product.Product(
                entity.getId().getValue(),
                entity.getName().getValue(),
                entity.getPrice(),
                entity.getBrand() != null ? entity.getBrand().getValue() : null,
                entity.getAmount(),
                supplier.get(),
                entity.isDeleted());

        repository.save(projection);
    }
}
