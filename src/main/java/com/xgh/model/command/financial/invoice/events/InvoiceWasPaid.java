package com.xgh.model.command.financial.invoice.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.model.command.financial.invoice.InvoiceId;
import com.xgh.model.command.financial.valueobjects.Transaction;
import java.util.List;

public class InvoiceWasPaid extends EntityEvent<InvoiceId> {
    private final List<Transaction> transactions;

    public InvoiceWasPaid(InvoiceId id, List<Transaction> transactions, EntityVersion entityVersion) {
        super(id, entityVersion);
        this.transactions = transactions;
    }

    public List<Transaction> getPaidValue() {
        return transactions;
    }
}
