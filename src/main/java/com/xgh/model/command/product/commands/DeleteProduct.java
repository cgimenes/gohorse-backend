package com.xgh.model.command.product.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.product.ProductId;

public class DeleteProduct implements Command {
    private static final long serialVersionUID = 717889692199957007L;

    private final ProductId id;

    protected DeleteProduct() {
        this.id = null;
    }

    public ProductId getId() {
        return id;
    }
}
