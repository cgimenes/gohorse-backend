package com.xgh.model.query.financial.report.cashflow;

import com.google.common.collect.Maps;
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

    public YearBalances() {
        this.balances = new HashMap<>();
    }

    public Map<Month, BigDecimal> getBalances() {
        return balances;
    }

    public BigDecimal getBalance(Month month) {
        return balances.get(month);
    }

    public YearBalances setBalance(Month month, BigDecimal balance) {
        Map<Month, BigDecimal> newBalances = Maps.newHashMap(this.balances);
        newBalances.put(month, balance);
        return new YearBalances(newBalances);
    }
}
