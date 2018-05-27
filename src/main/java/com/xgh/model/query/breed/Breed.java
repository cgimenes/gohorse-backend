package com.xgh.model.query.breed;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "breed")
public class Breed {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    protected Breed() {
    }

    public Breed(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
