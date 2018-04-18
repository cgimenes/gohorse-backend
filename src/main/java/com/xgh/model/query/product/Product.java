package com.xgh.model.query.product;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "product")
public final class Product {
    @Id
    private UUID id;

    private String name;

    private Float price;

    private String brand;

    private Float amount;

    @Column(name="supplier_id")
    private UUID supplierId;

    @JsonIgnore
    private Boolean deleted = false;

    public Product(UUID id, String name, Float price, String brand, Float amount, UUID supplierId, Boolean deleted) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.amount = amount;
        this.supplierId = supplierId;
        this.deleted = deleted;
    }

    protected Product() {}

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

    public UUID getSupplierId() {
        return supplierId;
    }

    public Boolean getDeleted() {
        return deleted;
    }
}
