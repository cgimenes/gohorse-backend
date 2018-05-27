package com.xgh.model.command.product.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.exceptions.EntityNotFoundException;
import com.xgh.model.command.product.Product;
import com.xgh.model.command.product.commands.RegisterProduct;
import com.xgh.model.command.supplier.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductRegistration implements CommandHandler<RegisterProduct> {

    private final EventStore eventStore;

    @Autowired
    public ProductRegistration(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    public void execute(RegisterProduct command) {
        Product product = new Product();
        if (!eventStore.entityExists(Supplier.class, command.getSupplierId())) {
            throw new EntityNotFoundException(Supplier.class.getSimpleName());
        }
        product.register(command.getId(), command.getName(), command.getPrice(), command.getBrand(),
                command.getAmount(), command.getSupplierId());
        eventStore.push(product);
    }
}
