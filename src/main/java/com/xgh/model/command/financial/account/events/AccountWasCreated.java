package com.xgh.model.command.financial.account.events;

import com.xgh.buildingblocks.entity.EntityId;
import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.model.command.financial.account.AccountId;
import com.xgh.model.command.financial.account.AccountType;

public class AccountWasCreated extends EntityEvent<AccountId> {
    private AccountType type;
    private EntityId accountOwner;

    protected AccountWasCreated() {
    }

    public AccountWasCreated(AccountId id, AccountType type, EntityId accountOwner, EntityVersion entityVersion) {
        super(id, entityVersion);
        this.type = type;
        this.accountOwner = accountOwner;
    }

    public AccountType getAccountType() {
        return type;
    }

    public EntityId getAccountOwner() {
        return accountOwner;
    }
}
