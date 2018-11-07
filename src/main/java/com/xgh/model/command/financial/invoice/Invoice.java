package com.xgh.model.command.financial.invoice;

import com.xgh.buildingblocks.entity.AggregateRoot;
import com.xgh.exceptions.InvalidArgumentException;
import com.xgh.model.command.financial.invoice.events.InvoiceWasCreated;
import com.xgh.model.command.financial.invoice.events.InvoiceWasPaid;
import com.xgh.model.command.financial.valueobjects.Transaction;
import com.xgh.model.command.financial.valueobjects.Operation;
import com.xgh.model.command.operational.valueobjects.OperationId;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Invoice extends AggregateRoot<InvoiceId> {
    private LocalDateTime issueDate;
    private BigDecimal totalValue;
    private Operation operation;
    private OperationId operationId;
    private InvoiceStatus status;
    private LocalDateTime paymentDate;
    private List<Transaction> transactions;
    private InvoiceType invoiceType;

    public void register(InvoiceId id, LocalDateTime issueDate, BigDecimal totalValue, Operation operation, OperationId operationId, List<Transaction> transactions, InvoiceType invoiceType) {
        recordAndApply(new InvoiceWasCreated(id, issueDate, totalValue, operation, operationId, transactions, invoiceType, nextVersion()));
    }

    public void pay() {
        if (this.status != InvoiceStatus.CREATED) {
            throw new IllegalStateException();
        }
        recordAndApply(new InvoiceWasPaid(id, transactions, LocalDateTime.now(), nextVersion()));
    }

    protected void when(InvoiceWasPaid event) {
        this.paymentDate = event.getPaymentDate();
        this.status = InvoiceStatus.PAID;
    }

    protected void when(InvoiceWasCreated event) {
        this.issueDate = event.getIssueDate();
        this.totalValue = event.getTotalValue();
        this.operation = event.getOperation();
        this.operationId = event.getOperationId();
        this.transactions = event.getTransactions();
        this.status = InvoiceStatus.CREATED;
        this.invoiceType = event.getInvoiceType();
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public Operation getOperation() {
        return operation;
    }

    public OperationId getOperationId() {
        return operationId;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public InvoiceStatus getStatus() {
        return status;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public InvoiceType getInvoiceType() {
        return invoiceType;
    }
}
