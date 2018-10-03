package com.xgh.model.command.financial.account;

import com.xgh.buildingblocks.entity.AggregateRoot;
import com.xgh.buildingblocks.entity.EntityId;
import com.xgh.model.command.financial.account.events.AccountWasCreated;
import com.xgh.model.command.financial.account.events.AccountWasDeleted;
import javax.validation.constraints.NotNull;

public class Account extends AggregateRoot<AccountId> {
    private AccountType type;
    private EntityId accountOwner;

    public void create(@NotNull AccountId id, @NotNull AccountType type, @NotNull EntityId accountOwner) {
        recordAndApply(new AccountWasCreated(id, type, accountOwner, nextVersion()));
    }

    public void delete() {
        recordAndApply(new AccountWasDeleted(id, nextVersion()));
    }

    protected void when(AccountWasCreated event) {
        this.type = event.getAccountType();
        this.accountOwner = event.getAccountOwner();
    }

    protected void when(AccountWasDeleted event) {
        this.markDeleted();
    }

    public AccountType getAccountType() {
        return type;
    }

    public EntityId getAccountOwner() {
        return accountOwner;
    }
}
