package com.xgh.model.command.operational.supplier.commands;

import com.xgh.buildingblocks.command.EntityCommand;
import com.xgh.model.command.operational.supplier.SupplierId;

public class DeleteSupplier implements EntityCommand {
    private final SupplierId id;

    protected DeleteSupplier() {
        this.id = null;
    }

    @Override
    public SupplierId getId() {
        return id;
    }
}
