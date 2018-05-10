package com.xgh.model.command.product.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.Event;
import com.xgh.model.command.product.ProductId;
import com.xgh.model.command.supplier.SupplierId;
import com.xgh.model.command.valueobjects.Name;

public class ProductWasUpdated extends Event<ProductId> {
    private final Name name;
    private final Float price;
    private final Name brand;
    private final SupplierId supplierId;

    protected ProductWasUpdated() {
        name = null;
        price = null;
        brand = null;
        supplierId = null;
    }

    public ProductWasUpdated(ProductId id, Name name, Float price, Name brand, SupplierId supplierId, EntityVersion entityVersion) {
        super(id, entityVersion);
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.supplierId = supplierId;
    }

    public Name getName() {
        return name;
    }

    public Float getPrice() {
        return price;
    }

    public Name getBrand() {
        return brand;
    }

    public SupplierId getSupplierId() {
        return supplierId;
    }
}
