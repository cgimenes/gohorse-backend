package com.xgh.model.command.financial.account.events;

import com.xgh.buildingblocks.entity.EntityId;
import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.model.command.financial.account.AccountId;
import com.xgh.model.command.financial.account.AccountType;

public class AccountWasCreated extends EntityEvent<AccountId> {
    private AccountType accountType;
    private EntityId accountOwner;

    protected AccountWasCreated() {
    }

    public AccountWasCreated(AccountId id, AccountType accountType, EntityId accountOwner, EntityVersion entityVersion) {
        super(id, entityVersion);
        this.accountType = accountType;
        this.accountOwner = accountOwner;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public EntityId getAccountOwner() {
        return accountOwner;
    }
}
