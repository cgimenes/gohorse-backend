package com.xgh.model.query.operational.supplier;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xgh.model.query.operational.address.Address;
import com.xgh.model.query.operational.enumerator.Enumerator;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "supplier")
public final class Supplier {
    @Id
    private UUID id;

    private String name;

    private String phone;

    @Column(name = "cpf_cnpj")
    private String document;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "distribution_type_id")
    private Enumerator distributionType;

    @JsonIgnore
    private Boolean deleted = false;

    protected Supplier() {
    }

    public Supplier(UUID id, String name, String phone, String document, Address address, Enumerator distributionType, Boolean deleted) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.phone = phone;
        this.address = address;
        this.distributionType = distributionType;
        this.deleted = deleted;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getDocument() {
        return document;
    }

    public Address getAddress() {
        return address;
    }

    public Enumerator getDistributionType() {
        return distributionType;
    }

    public Boolean getDeleted() {
        return deleted;
    }
}
