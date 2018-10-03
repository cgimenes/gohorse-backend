package com.xgh.model.command.financial.account.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.model.command.financial.account.AccountId;

public class AccountWasDeleted extends EntityEvent<AccountId> {
    public AccountWasDeleted(AccountId id, EntityVersion entityVersion) {
        super(id, entityVersion);
    }
}
