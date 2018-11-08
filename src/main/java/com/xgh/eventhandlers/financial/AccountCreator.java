package com.xgh.eventhandlers.financial;

import com.xgh.buildingblocks.command.CommandBus;
import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.buildingblocks.event.Event;
import com.xgh.buildingblocks.event.EventHandler;
import com.xgh.model.command.financial.account.AccountType;
import com.xgh.model.command.financial.account.commands.CreateAccount;
import com.xgh.model.command.operational.owner.events.OwnerWasRegistered;
import com.xgh.model.command.operational.supplier.events.SupplierWasRegistered;
import com.xgh.model.command.operational.veterinary.events.VeterinaryWasRegistered;
import org.springframework.stereotype.Component;

@Component
public class AccountCreator implements EventHandler {
    @Override
    public boolean isSubscribedTo(Event event) {
        return event instanceof OwnerWasRegistered
                || event instanceof VeterinaryWasRegistered
                || event instanceof SupplierWasRegistered;
    }

    @Override
    public void execute(Event event) {
        // TODO ver se a criação do id da conta, pode ficar nas entidades operacionais, assim teria
        // relacionamentos bidirecionais sem esforço

        CreateAccount command = new CreateAccount(
            getAccountType(event.getClass()),
            ((EntityEvent<?>) event).getEntityId()
        );
        CommandBus.dispatch(command);
    }

    private AccountType getAccountType(Class<? extends Event> eventClass) {
        String packageName = eventClass.getPackage().getName();
        if (packageName.contains("owner")) {
            return AccountType.CLIENT;
        } else if (packageName.contains("veterinary")) {
            return AccountType.ASSOCIATE;
        } else if (packageName.contains("supplier")) {
            return AccountType.SUPPLIER;
        }
        throw new RuntimeException("Tipo da conta não encontrada para a classe: " + eventClass.getName());
    }
}
