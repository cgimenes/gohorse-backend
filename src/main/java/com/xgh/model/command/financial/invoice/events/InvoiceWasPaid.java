package com.xgh.model.command.financial.invoice.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.model.command.financial.invoice.InvoiceId;
import com.xgh.model.command.financial.valueobjects.Transaction;
import java.time.LocalDateTime;
import java.util.List;

public class InvoiceWasPaid extends EntityEvent<InvoiceId> {
    private LocalDateTime paymentDate;
    private List<Transaction> transactions;

    protected InvoiceWasPaid() {
    }

    public InvoiceWasPaid(InvoiceId id, List<Transaction> transactions, LocalDateTime paymentDate, EntityVersion entityVersion) {
        super(id, entityVersion);
        this.paymentDate = paymentDate;
        this.transactions = transactions;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }
}
