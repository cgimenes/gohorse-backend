package com.xgh.model.veterinary.query;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xgh.model.address.query.Address;

@Entity
@Table(name = "veterinary")
public final class Veterinary {
	@Id
	private UUID id;
	
	private String name;
	
    @ManyToOne
    @Column(name = "address_id")
    private Address address;
	
	private String phone;
	
	private String crmv;
	
	private String email;
	
	@Column(name = "birth_date")
	private Date birthDate;
	
	@JsonIgnore
    private Boolean deleted = false;

	protected Veterinary() {}

	public Veterinary(UUID id, String name, Address address, String phone, String crmv, String email, Date birthDate, Boolean deleted) {
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

	public Date getBirthDate() {
		return this.birthDate;
	}
    
    public Boolean isDeleted() {
    	return deleted;
    }
}
