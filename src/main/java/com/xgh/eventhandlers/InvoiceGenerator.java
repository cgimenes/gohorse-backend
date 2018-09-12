package com.xgh.eventhandlers;

import com.xgh.buildingblocks.command.CommandBus;
import com.xgh.buildingblocks.event.Event;
import com.xgh.buildingblocks.event.EventHandler;
import com.xgh.model.command.financial.valueobjects.Installments;
import com.xgh.model.command.financial.valueobjects.Operation;
import com.xgh.model.command.financial.invoice.commands.GenerateInvoices;
import com.xgh.model.command.operational.internment.events.InternmentWasRegistered;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class InvoiceGenerator implements EventHandler {
    @Override
    public boolean isSubscribedTo(Event<?> event) {
        return event instanceof InternmentWasRegistered;
    }

    @Override
    public void execute(Event<?> event) {
        GenerateInvoices command = new GenerateInvoices(
                event.getEntityId(),
                getOperationByEvent(event),
                new BigDecimal(0),
                new Installments()
        );
        CommandBus.dispatch(command);
    }

    private Operation getOperationByEvent(Event<?> event) {
        return Operation.INTERNMENT;
    }
}
