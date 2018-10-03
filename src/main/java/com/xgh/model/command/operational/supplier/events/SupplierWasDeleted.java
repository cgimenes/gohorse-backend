package com.xgh.model.command.operational.supplier.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.model.command.operational.supplier.SupplierId;

public class SupplierWasDeleted extends EntityEvent<SupplierId> {
    protected SupplierWasDeleted() {
    }

    public SupplierWasDeleted(SupplierId id, EntityVersion version) {
        super(id, version);
    }
}
