package com.xgh.model.command.product.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.product.Product;
import com.xgh.model.command.product.commands.UpdateProduct;

public class ProductUpdate implements CommandHandler<UpdateProduct>{

    private final EventStore repository;

    public ProductUpdate(EventStore repository) {
        this.repository = repository;
    }

    @Override
    public void execute(UpdateProduct command) {
        Product product = repository.pull(Product.class, command.getId());
        // verificar se o supplierId existe
        product.update(command.getName(), command.getPrice(), command.getBrand(), command.getSupplierId());
        repository.push(product);
    }
}
