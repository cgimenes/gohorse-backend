package com.xgh.model.query.address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xgh.model.query.address.postalcode.PostalCode;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @JsonIgnore
    private final UUID id = UUID.randomUUID();

    @ManyToOne
    @JoinColumn(name = "postalcode_id")
    private PostalCode postalCode;

    private Integer number;

    private String complement;

    protected Address() {
    }

    public Address(PostalCode postalCode, Integer number, String complement) {
        this.postalCode = postalCode;
        this.number = number;
        this.complement = complement;
    }

    public UUID getId() {
        return id;
    }

    public PostalCode getPostalCode() {
        return postalCode;
    }

    public Integer getNumber() {
        return number;
    }

    public String getComplement() {
        return complement;
    }
}
