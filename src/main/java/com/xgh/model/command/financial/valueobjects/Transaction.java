package com.xgh.model.command.financial.valueobjects;

import com.xgh.buildingblocks.valueobject.ValueObject;
import com.xgh.model.command.financial.account.Account;
import java.math.BigDecimal;

public class Transaction implements ValueObject {
    private Account origin;
    private Account destination;
    private BigDecimal value;

    public Transaction(Account origin, Account destination, BigDecimal value) {
        this.origin = origin;
        this.destination = destination;
        this.value = value;
    }

    public Account getOrigin() {
        return origin;
    }

    public Account getDestination() {
        return destination;
    }

    public BigDecimal getValue() {
        return value;
    }
}
