package com.xgh.model.command.financial.invoice;

import com.xgh.buildingblocks.entity.AggregateRoot;
import com.xgh.buildingblocks.entity.EntityId;
import com.xgh.model.command.financial.invoice.events.InvoiceWasCreated;
import com.xgh.model.command.financial.invoice.events.InvoiceWasPaid;
import com.xgh.model.command.financial.valueobjects.Transaction;
import com.xgh.model.command.financial.valueobjects.Operation;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Invoice extends AggregateRoot<InvoiceId> {
    private LocalDateTime issueDate;
    private BigDecimal totalValue;
    private Operation operation;
    // TODO ver se é necessário criar um supertipo para o identificador da operação, para melhorar a expressão do código
    private EntityId operationId;
    private InvoiceStatus status;
    private List<Transaction> transactions;

    public void register(InvoiceId id, LocalDateTime issueDate, BigDecimal totalValue, Operation operation, EntityId operationId, List<Transaction> transactions) {
        recordAndApply(new InvoiceWasCreated(id, issueDate, totalValue, operation, operationId, transactions, nextVersion()));
    }

    public void pay() {
        recordAndApply(new InvoiceWasPaid(id, transactions, nextVersion()));
    }

    protected void when(InvoiceWasPaid event) {
        this.status = InvoiceStatus.PAID;
    }

    protected void when(InvoiceWasCreated event) {
        this.issueDate = event.getIssueDate();
        this.totalValue = event.getTotalValue();
        this.operation = event.getOperation();
        this.operationId = event.getOperationId();
        this.transactions = event.getTransactions();
        this.status = InvoiceStatus.CREATED;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public Operation getOperation() {
        return operation;
    }

    public EntityId getOperationId() {
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
}
