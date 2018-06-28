package com.xgh.model.command.supplier.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.Event;
import com.xgh.model.command.supplier.SupplierId;

public class SupplierWasDeleted extends Event<SupplierId> {
    protected SupplierWasDeleted() {
    }

    public SupplierWasDeleted(SupplierId id, EntityVersion version) {
        super(id, version);
    }
}
