package com.xgh.model.command.supplier;

import com.xgh.buildingblocks.entity.AggregateRoot;
import com.xgh.exceptions.NullMandatoryArgumentException;
import com.xgh.model.command.supplier.events.SupplierWasCreated;

public class Supplier extends AggregateRoot<SupplierId> {
    public void register(SupplierId id) {
        if (id == null) {
            throw new NullMandatoryArgumentException("id");
        }
        recordAndApply(new SupplierWasCreated(id, this.nextVersion()));
    }

    protected void when(SupplierWasCreated event) {
    }
}
