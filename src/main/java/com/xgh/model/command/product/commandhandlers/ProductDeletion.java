package com.xgh.model.command.product.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.product.Product;
import com.xgh.model.command.product.commands.DeleteProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductDeletion implements CommandHandler<DeleteProduct>{

    private final EventStore repository;

    @Autowired
    public ProductDeletion(EventStore repository) {
        this.repository = repository;
    }

    @Override
    public void execute(DeleteProduct command) {
        Product product = repository.pull(Product.class, command.getId());
        product.delete();
        repository.push(product);
    }
}
