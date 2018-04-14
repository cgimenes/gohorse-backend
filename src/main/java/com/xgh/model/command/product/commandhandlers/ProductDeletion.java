package com.xgh.model.command.product.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.product.Product;
import com.xgh.model.command.product.commands.DeleteProduct;

public class ProductDeletion implements CommandHandler<DeleteProduct>{

    private final EventStore repository;

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
