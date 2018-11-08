package com.xgh.model.command.financial.valueobjects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.util.List;

public class Installments {
    private List<Installment> installmentList;

    public Installments(List<Installment> installmentList) {
        this.installmentList = installmentList;
    }

    @JsonIgnore
    public BigDecimal getTotalValue() {
        return installmentList
                .stream()
                .map(Installment::getValue)
                .reduce(BigDecimal::add)
                .orElseGet(() -> new BigDecimal(0));
    }

    public List<Installment> getInstallmentList() {
        return installmentList;
    }

    public Integer getQuantity() {
        return installmentList.size();
    }
}
