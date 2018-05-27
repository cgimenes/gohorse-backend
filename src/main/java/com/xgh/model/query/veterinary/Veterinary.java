package com.xgh.model.query.veterinary;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xgh.model.query.address.Address;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "veterinary")
public final class Veterinary {
    @Id
    private UUID id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    private String phone;

    private String crmv;

    private String email;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @JsonIgnore
    private Boolean deleted = false;

    protected Veterinary() {
    }

    public Veterinary(UUID id, String name, Address address, String phone, String crmv, String email, LocalDate birthDate, Boolean deleted) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.crmv = crmv;
        this.email = email;
        this.birthDate = birthDate;
        this.deleted = deleted;
    }

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getCrmv() {
        return this.crmv;
    }

    public String getEmail() {
        return this.email;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public Boolean isDeleted() {
        return deleted;
    }
}
