package com.xgh.model.query.financial.invoice;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "invoice")
public class Invoice {
    @Id
    private UUID id;

    private LocalDateTime issueDate;
    private LocalDateTime paymentDate;
    private BigDecimal totalValue;
    private String operation;
    private UUID operationId;
    private String status;
    private String type;

    protected Invoice() {
    }

    public Invoice(UUID id, LocalDateTime issueDate, LocalDateTime paymentDate, BigDecimal totalValue, String operation, UUID operationId, String status, String type) {
        this.id = id;
        this.issueDate = issueDate;
        this.paymentDate = paymentDate;
        this.totalValue = totalValue;
        this.operation = operation;
        this.operationId = operationId;
        this.status = status;
        this.type = type;
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public String getOperation() {
        return operation;
    }

    public UUID getOperationId() {
        return operationId;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public String getType() {
        return type;
    }

//    public List<Transaction> getTransactions() {
//        return transactions;
//    }
}
