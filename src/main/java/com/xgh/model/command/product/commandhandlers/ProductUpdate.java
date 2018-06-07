package com.xgh.model.command.product.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.exceptions.EntityNotFoundException;
import com.xgh.model.command.product.Product;
import com.xgh.model.command.product.commands.UpdateProduct;
import com.xgh.model.command.supplier.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductUpdate implements CommandHandler<UpdateProduct> {

    private final EventStore eventStore;

    @Autowired
    public ProductUpdate(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    public void execute(UpdateProduct command) {
        Product product = eventStore.pull(Product.class, command.getId());
        if (!eventStore.entityExists(Supplier.class, command.getSupplierId())) {
            throw new EntityNotFoundException(Supplier.class.getSimpleName());
        }
        product.update(command.getName(), command.getPrice(), command.getBrand(), command.getSupplierId());
        eventStore.push(product);
    }
}
