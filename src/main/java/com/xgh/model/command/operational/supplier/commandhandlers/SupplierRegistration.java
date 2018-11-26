package com.xgh.model.command.operational.supplier.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.operational.supplier.Supplier;
import com.xgh.model.command.operational.supplier.commands.RegisterSupplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SupplierRegistration implements CommandHandler<RegisterSupplier> {
    private final EventStore repository;

    @Autowired
    public SupplierRegistration(EventStore repository) {
        this.repository = repository;
    }

    @Override
    public void execute(RegisterSupplier command) {
        Supplier supplier = new Supplier();
        supplier.register(
                command.getId(),
                command.getName(),
                command.getPhone(),
                command.getDistributionType(),
                command.getDocument().getValue(),
                command.getAddress());
        repository.push(supplier);
    }
}
