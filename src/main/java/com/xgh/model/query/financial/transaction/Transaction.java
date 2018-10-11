package com.xgh.model.query.financial.transaction;

import com.xgh.model.query.financial.account.Account;
import com.xgh.model.query.financial.invoice.Invoice;
import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "origin_id")
    private Account origin;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Account destination;

    private BigDecimal value;

    protected Transaction() {
    }

    public Transaction(UUID id, Invoice invoice, Account origin, Account destination, BigDecimal value) {
        this.id = id;
        this.invoice = invoice;
        this.origin = origin;
        this.destination = destination;
        this.value = value;
    }

    public UUID getId() {
        return id;
    }

    public Account getOrigin() {
        return origin;
    }

    public Account getDestination() {
        return destination;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Invoice getInvoice() {
        return invoice;
    }
}
