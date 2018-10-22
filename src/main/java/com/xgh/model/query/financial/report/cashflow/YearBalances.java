package com.xgh.model.query.financial.report.cashflow;

import java.math.BigDecimal;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

public class YearBalances {
    private Map<Month, BigDecimal> balances;

    public YearBalances(Map<Month, BigDecimal> balances) {
        this.balances = new HashMap<>();
        this.balances.putAll(balances);
    }

    public Map<Month, BigDecimal> getBalances() {
        return balances;
    }

    public BigDecimal getBalance(Month month) {
        return balances.get(month);
    }
}
