package com.xgh.eventhandlers.financial;

import com.xgh.buildingblocks.command.CommandBus;
import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.buildingblocks.event.Event;
import com.xgh.buildingblocks.event.EventHandler;
import com.xgh.model.command.financial.valueobjects.Operation;
import com.xgh.model.command.financial.invoice.commands.GenerateInvoices;
import com.xgh.model.command.operational.internment.events.InternmentWasRegistered;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class InvoiceGenerator implements EventHandler {
    @Override
    public boolean isSubscribedTo(Event event) {
        return event instanceof InternmentWasRegistered;
    }

    @Override
    public void execute(Event event) {
        GenerateInvoices command = new GenerateInvoices(
                ((EntityEvent<?>) event).getEntityId(),
                Operation.INTERNMENT,
                new BigDecimal(10)
        );
        CommandBus.dispatch(command);
    }
}
