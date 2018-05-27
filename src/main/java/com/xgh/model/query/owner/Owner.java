package com.xgh.model.query.owner;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xgh.model.query.address.Address;
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

    private String cpf;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @JsonIgnore
    private Boolean deleted = false;

    protected Owner() {
    }

    public Owner(UUID id, String name, String cpf, String phone, LocalDate birthDate, Address address, Boolean deleted) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
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

    public String getCpf() {
        return cpf;
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
