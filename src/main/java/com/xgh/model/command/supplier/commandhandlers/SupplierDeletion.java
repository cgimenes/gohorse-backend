package com.xgh.model.command.supplier.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.supplier.Supplier;
import com.xgh.model.command.supplier.commands.DeleteSupplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SupplierDeletion implements CommandHandler<DeleteSupplier> {
    private final EventStore repository;

    @Autowired
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
