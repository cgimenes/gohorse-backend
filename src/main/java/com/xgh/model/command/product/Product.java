package com.xgh.model.command.product;

import com.xgh.buildingblocks.entity.AggregateRoot;
import com.xgh.exceptions.NullMandatoryArgumentException;
import com.xgh.model.command.product.events.ProductWasDeleted;
import com.xgh.model.command.product.events.ProductWasRegistered;
import com.xgh.model.command.product.events.ProductWasUpdated;
import com.xgh.model.command.supplier.SupplierId;
import com.xgh.model.command.valueobjects.Name;

public class Product extends AggregateRoot<ProductId> {
    private static final long serialVersionUID = 6667051063449205999L;

    private Name name;
    private Float price;
    private Name brand;
    private Float amount;
    private SupplierId supplierId;

    public void register(ProductId id, Name name, Float price, Name brand, Float amount, SupplierId supplierId) {
        if (id == null) {
            throw new NullMandatoryArgumentException("id");
        }

        if (name == null) {
            throw new NullMandatoryArgumentException("nome");
        }

        if (price == null) {
            throw new NullMandatoryArgumentException("preço");
        }

        if (amount == null) {
            throw new NullMandatoryArgumentException("quantidade inicial");
        }

        if (supplierId == null) {
            throw new NullMandatoryArgumentException("fornecedor");
        }

        recordAndApply(new ProductWasRegistered(id, name, price, brand, amount, supplierId, this.nextVersion()));
    }

    public void update(Name name, Float price, Name brand, SupplierId supplierId) {
        recordAndApply(new ProductWasUpdated(this.id, name, price, brand, supplierId, this.nextVersion()));
    }

    public void delete() {
        recordAndApply(new ProductWasDeleted(this.id, this.nextVersion()));
    }

    protected void when(ProductWasRegistered event) {
        this.name = event.getName();
        this.price = event.getPrice();
        this.brand = event.getBrand();
        this.amount = event.getAmount();
        this.supplierId = event.getSupplierId();
    }

    protected void when(ProductWasUpdated event) {
        this.name = event.getName();
        this.price = event.getPrice();
        this.brand = event.getBrand();
        this.supplierId = event.getSupplierId();
    }

    protected void when(ProductWasDeleted event) {
        this.markDeleted();
    }

    public Product() {
        super();
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
