package com.xgh.model.command.operational.product.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.operational.product.ProductId;
import com.xgh.model.command.operational.supplier.SupplierId;
import com.xgh.model.command.operational.valueobjects.Name;

public class RegisterProduct implements Command {
    private final ProductId id;
    private final Name name;
    private final Float price;
    private final Name brand;
    private final Float amount;
    private final SupplierId supplierId;

    protected RegisterProduct() {
        id = new ProductId();
        name = null;
        price = null;
        brand = null;
        amount = null;
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

    public Float getAmount() {
        return amount;
    }

    public SupplierId getSupplierId() {
        return supplierId;
    }
}
