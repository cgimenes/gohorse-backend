package com.xgh.model.command.operational.product.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.operational.product.ProductId;

public class DeleteProduct implements Command {
    private final ProductId id;

    protected DeleteProduct() {
        this.id = null;
    }

    @Override
    public ProductId getId() {
        return id;
    }
}
