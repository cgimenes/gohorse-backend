package com.xgh.model.command.operational.product.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.model.command.operational.product.ProductId;

public class ProductWasDeleted extends EntityEvent<ProductId> {
    protected ProductWasDeleted() {
    }

    public ProductWasDeleted(ProductId id, EntityVersion version) {
        super(id, version);
    }
}
