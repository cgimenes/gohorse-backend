package com.xgh.model.command.operational.supplier.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.operational.supplier.Supplier;
import com.xgh.model.command.operational.supplier.commands.UpdateSupplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SupplierUpdate implements CommandHandler<UpdateSupplier> {
    private final EventStore repository;

    @Autowired
    public SupplierUpdate(EventStore repository) {
        this.repository = repository;
    }

    @Override
    public void execute(UpdateSupplier command) {
        Supplier supplier = repository.pull(Supplier.class, command.getId());
        supplier.update(
                command.getName(),
                command.getPhone(),
                command.getDocument().getValue(),
                command.getAddress(),
                command.getDistributionType());
        repository.push(supplier);
    }
}
