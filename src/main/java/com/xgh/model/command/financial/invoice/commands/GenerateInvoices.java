package com.xgh.model.command.financial.invoice.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.buildingblocks.entity.EntityId;
import com.xgh.model.command.financial.valueobjects.Operation;
import java.math.BigDecimal;

public class GenerateInvoices implements Command {
    private final EntityId operationId;
    private final Operation operation;
    private final BigDecimal value;

    public GenerateInvoices(EntityId operationId, Operation operation, BigDecimal value) {
        this.operationId = operationId;
        this.operation = operation;
        this.value = value;
    }

    public EntityId getOperationId() {
        return operationId;
    }

    public Operation getOperation() {
        return operation;
    }

    public BigDecimal getValue() {
        return value;
    }
}
