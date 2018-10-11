package com.xgh.model.command.financial.invoice.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.financial.valueobjects.Operation;
import com.xgh.model.command.operational.valueobjects.OperationId;
import java.math.BigDecimal;

public class GenerateInvoices implements Command {
    private final OperationId operationId;
    private final Operation operation;
    private final BigDecimal value;

    public GenerateInvoices(OperationId operationId, Operation operation, BigDecimal value) {
        this.operationId = operationId;
        this.operation = operation;
        this.value = value;
    }

    public OperationId getOperationId() {
        return operationId;
    }

    public Operation getOperation() {
        return operation;
    }

    public BigDecimal getValue() {
        return value;
    }
}
