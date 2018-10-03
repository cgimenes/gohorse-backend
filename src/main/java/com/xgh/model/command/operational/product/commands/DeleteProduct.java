package com.xgh.model.command.operational.product.commands;

import com.xgh.buildingblocks.command.EntityCommand;
import com.xgh.model.command.operational.product.ProductId;

public class DeleteProduct implements EntityCommand {
    private final ProductId id;

    protected DeleteProduct() {
        this.id = null;
    }

    @Override
    public ProductId getId() {
        return id;
    }
}
