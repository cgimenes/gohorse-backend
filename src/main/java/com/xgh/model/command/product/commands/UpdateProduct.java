package com.xgh.model.command.product.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.product.ProductId;
import com.xgh.model.command.supplier.SupplierId;
import com.xgh.model.command.valueobjects.Name;

public class UpdateProduct implements Command {
    private final ProductId id;
    private final Name name;
    private final Float price;
    private final Name brand;
    private final SupplierId supplierId;

    protected UpdateProduct() {
        id = null;
        name = null;
        price = null;
        brand = null;
        supplierId = null;
    }

    @Override
    public ProductId getId() {
        return id;
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
