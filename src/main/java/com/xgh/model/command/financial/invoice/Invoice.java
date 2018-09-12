package com.xgh.model.command.financial.invoice;

import com.xgh.buildingblocks.entity.EntityId;
import com.xgh.model.command.financial.valueobjects.Installments;
import com.xgh.model.command.financial.valueobjects.Operation;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Invoice {
    private InvoiceId id;
    private Installments installments;
    private LocalDateTime issueDate;
    private BigDecimal totalValue;
    private Operation operation;
    // TODO ver se é necessário criar um supertipo para o identificador da operação, para melhorar a expressão do código
    private EntityId operationId;
    private InvoiceType type;
}
