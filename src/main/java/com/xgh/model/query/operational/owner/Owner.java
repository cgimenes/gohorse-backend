package com.xgh.model.query.operational.owner;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xgh.model.query.operational.address.Address;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "owner")
public final class Owner {
    @Id
    private UUID id;

    private String name;

    private String phone;

    @Column(name = "cpf")
    private String document;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @JsonIgnore
    private Boolean deleted = false;

    protected Owner() {
    }

    public Owner(UUID id, String name, String document, String phone, LocalDate birthDate, Address address, Boolean deleted) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.phone = phone;
        this.birthDate = birthDate;
        this.address = address;
        this.deleted = deleted;
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

    public UUID getId() {
        return id;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Address getAddress() {
        return address;
    }

    public Boolean isDeleted() {
        return deleted;
    }
}
