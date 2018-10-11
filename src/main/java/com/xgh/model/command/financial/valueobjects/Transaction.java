package com.xgh.model.command.financial.valueobjects;

import com.xgh.buildingblocks.valueobject.ValueObject;
import com.xgh.model.command.financial.account.AccountId;
import java.math.BigDecimal;

public class Transaction implements ValueObject {
    private AccountId origin;
    private AccountId destination;
    private BigDecimal value;

    protected Transaction() {
    }

    public Transaction(AccountId origin, AccountId destination, BigDecimal value) {
        this.origin = origin;
        this.destination = destination;
        this.value = value;
    }

    public AccountId getOrigin() {
        return origin;
    }

    public AccountId getDestination() {
        return destination;
    }

    public BigDecimal getValue() {
        return value;
    }
}
