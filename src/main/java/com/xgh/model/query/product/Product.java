package com.xgh.model.query.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xgh.model.query.supplier.Supplier;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public final class Product {
    @Id
    private UUID id;

    private String name;

    private Float price;

    private String brand;

    private Float amount;

    @JoinColumn(name = "supplier_id")
    @ManyToOne
    private Supplier supplier;

    @JsonIgnore
    private Boolean deleted = false;

    public Product(UUID id, String name, Float price, String brand, Float amount, Supplier supplier, Boolean deleted) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.amount = amount;
        this.supplier = supplier;
        this.deleted = deleted;
    }

    protected Product() {
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Float getPrice() {
        return price;
    }

    public String getBrand() {
        return brand;
    }

    public Float getAmount() {
        return amount;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public Boolean getDeleted() {
        return deleted;
    }
}
