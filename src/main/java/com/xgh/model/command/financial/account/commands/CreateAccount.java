package com.xgh.model.command.financial.account.commands;

import com.xgh.buildingblocks.command.EntityCommand;
import com.xgh.buildingblocks.entity.EntityId;
import com.xgh.model.command.financial.account.AccountId;
import com.xgh.model.command.financial.account.AccountType;

public class CreateAccount implements EntityCommand {
    private AccountId id;
    private AccountType type;
    private EntityId accountOwner;

    protected CreateAccount() {
    }

    public CreateAccount(AccountId id, AccountType type, EntityId accountOwner) {
        this.id = id;
        this.type = type;
        this.accountOwner = accountOwner;
    }

    public CreateAccount(AccountType type, EntityId accountOwner) {
        this(new AccountId(), type, accountOwner);
    }

    @Override
    public AccountId getId() {
        return id;
    }

    public AccountType getAccountType() {
        return type;
    }

    public EntityId getAccountOwner() {
        return accountOwner;
    }
}
