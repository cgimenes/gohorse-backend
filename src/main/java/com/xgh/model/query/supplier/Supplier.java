package com.xgh.model.query.supplier;

<<<<<<< HEAD
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xgh.model.query.address.Address;
import com.xgh.model.query.distributionType.DistributionType;

@Entity
@Table(name = "supplier")
public final class Supplier {
    @Id
    private UUID id;

    private String name;

    private String phone;

    private String cpfCnpj;    

    @ManyToOne
    @JoinColumn(name="address_id")
    private Address address;
        
    @ManyToOne
    @JoinColumn(name="distribution_type_id")
    private DistributionType distributionType;
    
	@JsonIgnore
    private Boolean deleted = false;

    protected Supplier() {}

    public Supplier(UUID id, String name, String phone, String cpfCnpj, Address address, DistributionType distributionType, Boolean deleted) {
        this.id = id;
        this.name = name;
        this.cpfCnpj = cpfCnpj;
        this.phone = phone;        
        this.address = address;
        this.distributionType = distributionType;
        this.deleted = deleted;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }
=======
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

    protected Supplier() {}
>>>>>>> origin/master

    public UUID getId() {
        return id;
    }
<<<<<<< HEAD
    
    public Address getAddress() {
        return address;
    }

    public DistributionType getDistributionType() {
		return distributionType;
	}

    public Boolean isDeleted() {
        return deleted;
    }
=======
>>>>>>> origin/master
}
