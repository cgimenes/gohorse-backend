package com.xgh.eventhandlers.financial;

import com.xgh.buildingblocks.command.CommandBus;
import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.buildingblocks.event.Event;
import com.xgh.buildingblocks.event.EventHandler;
import com.xgh.model.command.financial.valueobjects.Operation;
import com.xgh.model.command.financial.invoice.commands.GenerateInvoices;
import com.xgh.model.command.operational.Priceable;
import com.xgh.model.command.operational.appointment.events.AppointmentWasFinished;
import com.xgh.model.command.operational.valueobjects.OperationId;
import org.springframework.stereotype.Component;

@Component
public class InvoiceGenerator implements EventHandler {
    @Override
    public boolean isSubscribedTo(Event event) {
        return event instanceof AppointmentWasFinished;
    }

    @Override
    public void execute(Event event) {
        GenerateInvoices command = new GenerateInvoices(
                new OperationId(((EntityEvent<?>) event).getEntityId().getValue()),
                Operation.APPOINTMENT,
                ((Priceable) event).getPrice()
        );
        CommandBus.dispatch(command);
    }
}
