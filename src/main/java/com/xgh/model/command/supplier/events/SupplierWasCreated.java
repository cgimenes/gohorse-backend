package com.xgh.model.command.supplier.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.Event;
import com.xgh.model.command.supplier.SupplierId;

public class SupplierWasCreated extends Event<SupplierId> {
    private static final long serialVersionUID = 7509389707864790428L;

    public SupplierWasCreated(SupplierId id, EntityVersion version) {
        super(id, version);
    }

    protected SupplierWasCreated() {}
}
