package com.xgh.model.query.distributionType;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "distribution_type")
public class DistributionType {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    protected DistributionType() {}

    public DistributionType(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
}
