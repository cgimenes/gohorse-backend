package com.xgh.model.command.financial.invoice.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.buildingblocks.entity.EntityId;
import com.xgh.model.command.financial.valueobjects.Installments;
import com.xgh.model.command.financial.valueobjects.Operation;
import java.math.BigDecimal;

public class GenerateInvoices implements Command {
    private final EntityId operationId;
    private final Operation operation;
    private final BigDecimal value;
    private final Installments installments;

    public GenerateInvoices(EntityId operationId, Operation operation, BigDecimal value, Installments installments) {
        this.operationId = operationId;
        this.operation = operation;
        this.value = value;
        this.installments = installments;
    }

    @Override
    public EntityId getId() {
        return operationId;
    }

    public Operation getOperation() {
        return operation;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Installments getInstallments() {
        return installments;
    }
}
