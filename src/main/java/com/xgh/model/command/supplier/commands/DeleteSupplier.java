package com.xgh.model.command.supplier.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.supplier.SupplierId;

public class DeleteSupplier implements Command {

	private static final long serialVersionUID = -4733864348075219484L;
	
	private final SupplierId id;
	
	protected DeleteSupplier() {
        this.id = null;
    }

    public SupplierId getId() {
        return id;
    }
}
