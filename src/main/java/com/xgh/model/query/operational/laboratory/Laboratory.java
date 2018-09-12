package com.xgh.model.query.operational.laboratory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xgh.model.query.operational.address.Address;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

// TODO criar uma classe base para entidades do query model
@Entity
@Table(name = "laboratory")
public final class Laboratory {
    @Id
    private UUID id;

    @Column(name = "company_name")
    private String companyName;

    private String phone;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @JsonIgnore
    private Boolean deleted = false;

    protected Laboratory() {
    }

    public Laboratory(UUID id, String companyName, String phone, Address address, Boolean deleted) {
        this.id = id;
        this.companyName = companyName;
        this.phone = phone;
        this.address = address;
        this.deleted = deleted;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getPhone() {
        return phone;
    }

    public Address getAddress() {
        return address;
    }

    public UUID getId() {
        return id;
    }

    public Boolean isDeleted() {
        return deleted;
    }
}
