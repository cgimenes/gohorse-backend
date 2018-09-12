package com.xgh.model.query.operational.address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xgh.model.query.operational.address.postalcode.PostalCode;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @JsonIgnore
    private final UUID id = UUID.randomUUID();

    @ManyToOne
    @JoinColumn(name = "postal_code_id")
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
