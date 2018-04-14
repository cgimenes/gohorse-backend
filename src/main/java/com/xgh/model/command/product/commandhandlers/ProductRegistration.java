package com.xgh.model.command.product.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.product.Product;
import com.xgh.model.command.product.commands.RegisterProduct;

public class ProductRegistration implements CommandHandler<RegisterProduct>{

    private final EventStore repository;

    public ProductRegistration(EventStore repository) {
        this.repository = repository;
    }

    @Override
    public void execute(RegisterProduct command) {
        Product product = new Product();
        // verificar se o supplierId existe
        product.register(command.getId(), command.getName(), command.getPrice(), command.getBrand(),
                command.getAmount(), command.getSupplierId());
        repository.push(product);
    }
}
