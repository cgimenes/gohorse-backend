package com.xgh.model.query.supplier;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "supplier")
public class Supplier {
    @Id
    @JsonIgnore
    private UUID id = UUID.randomUUID();

    public Supplier(UUID id) {
        this.id = id;
    }

    protected Supplier() {
    }

    public UUID getId() {
        return id;
    }
}
