package com.xgh.model.command.financial.invoice.events;

import com.xgh.buildingblocks.entity.EntityId;
import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.model.command.financial.invoice.InvoiceId;
import com.xgh.model.command.financial.valueobjects.Transaction;
import com.xgh.model.command.financial.valueobjects.Operation;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class InvoiceWasCreated extends EntityEvent<InvoiceId> {
    private LocalDateTime issueDate;
    private BigDecimal totalValue;
    private Operation operation;
    private EntityId operationId;
    private final List<Transaction> transactions;

    public InvoiceWasCreated(InvoiceId id, LocalDateTime issueDate, BigDecimal totalValue, Operation operation, EntityId operationId, List<Transaction> transactions, EntityVersion entityVersion) {
        super(id, entityVersion);
        this.issueDate = issueDate;
        this.totalValue = totalValue;
        this.operation = operation;
        this.operationId = operationId;
        this.transactions = transactions;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
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

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
