package com.xgh.model.command.financial.account;

import com.xgh.buildingblocks.entity.EntityId;
import java.util.UUID;

public class AccountId extends EntityId {
    public AccountId() {
        super();
    }

    public AccountId(UUID id) {
        super(id);
    }

    public AccountId(String id) {
        super(id);
    }
}
