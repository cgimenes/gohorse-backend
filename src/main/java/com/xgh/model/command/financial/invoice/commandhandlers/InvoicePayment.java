package com.xgh.model.command.financial.invoice.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.financial.invoice.Invoice;
import com.xgh.model.command.financial.invoice.commands.PayInvoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InvoicePayment implements CommandHandler<PayInvoice> {
    private EventStore eventStore;

    @Autowired
    public InvoicePayment(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    public void execute(PayInvoice command) {
        Invoice invoice = eventStore.pull(Invoice.class, command.getId());
        invoice.pay();
        eventStore.push(invoice);
    }
}
