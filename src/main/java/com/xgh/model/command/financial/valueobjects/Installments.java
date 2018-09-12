package com.xgh.model.command.financial.valueobjects;

import java.math.BigDecimal;
import java.util.List;

public class Installments {
    private List<Installment> installmentList;

    public BigDecimal getTotalValue() {
        return installmentList
                .stream()
                .map(Installment::getValue)
                .reduce(BigDecimal::add)
                .orElseGet(() -> new BigDecimal(0));
    }

    public Integer getQuantity() {
        return installmentList.size();
    }
}
