package com.xgh.model.command.product.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.Event;
import com.xgh.model.command.product.ProductId;

public class ProductWasDeleted extends Event<ProductId> {
    private static final long serialVersionUID = 2436008051899085149L;

    protected ProductWasDeleted() {}

    public ProductWasDeleted(ProductId id, EntityVersion version) {
        super(id, version);
    }
}
