package com.xgh.model.command.financial.invoice.commands;

import com.xgh.buildingblocks.command.EntityCommand;
import com.xgh.model.command.financial.invoice.InvoiceId;

public class PayInvoice implements EntityCommand {
    private final InvoiceId id;

    protected PayInvoice() {
        id = null;
    }

    public PayInvoice(InvoiceId id) {
        this.id = id;
    }

    @Override
    public InvoiceId getId() {
        return id;
    }
}
