package com.xgh.model.command.operational.product.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.operational.product.Product;
import com.xgh.model.command.operational.product.commands.DeleteProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductDeletion implements CommandHandler<DeleteProduct> {

    private final EventStore eventStore;

    @Autowired
    public ProductDeletion(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    public void execute(DeleteProduct command) {
        Product product = eventStore.pull(Product.class, command.getId());
        product.delete();
        eventStore.push(product);
    }
}
