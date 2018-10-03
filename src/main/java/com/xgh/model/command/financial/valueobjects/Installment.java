package com.xgh.model.command.financial.valueobjects;

import java.math.BigDecimal;

public class Installment {
    private BigDecimal value;

    public Installment(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }
}
