package com.xgh.model.command.product;

import com.xgh.buildingblocks.entity.EntityId;

import java.util.UUID;

public class ProductId extends EntityId {
    private static final long serialVersionUID = -9005053215667683283L;

    public ProductId(String value) {
        super(value);
    }

    public ProductId(UUID value) {
        super(value);
    }

    public ProductId() {
        super();
    }
}
