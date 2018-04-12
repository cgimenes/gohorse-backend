package com.xgh.model.command.owner;

import com.xgh.buildingblocks.entity.EntityId;

import java.util.UUID;

public class OwnerId extends EntityId {
    private static final long serialVersionUID = -3894150224399325753L;

    public OwnerId() {
        super();
    }

    public OwnerId(String value) {
        super(value);
    }

    public OwnerId(UUID value) {
        super(value);
    }
}
