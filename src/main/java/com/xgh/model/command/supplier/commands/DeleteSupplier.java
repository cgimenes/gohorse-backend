package com.xgh.model.command.supplier.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.owner.OwnerId;

public class DeleteSupplier implements Command {
	private static final long serialVersionUID = -8751170354092451583L;
	private final OwnerId id;
	
	protected DeleteSupplier() {
        this.id = null;
    }

    public OwnerId getId() {
        return id;
    }
}
