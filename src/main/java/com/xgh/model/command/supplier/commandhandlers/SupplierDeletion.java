package com.xgh.model.command.supplier.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.supplier.Supplier;
import com.xgh.model.command.supplier.commands.DeleteSupplier;

public class SupplierDeletion implements CommandHandler<DeleteSupplier>{
	private EventStore repository;
	
	public SupplierDeletion(EventStore repository) {
		this.repository = repository;
	}
	
	@Override
	public void execute(DeleteSupplier command) {
		Supplier supplier = repository.pull(Supplier.class, command.getId());
		supplier.delete();
		repository.push(supplier);
	}
}
