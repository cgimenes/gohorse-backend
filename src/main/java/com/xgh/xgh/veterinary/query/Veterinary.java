package com.xgh.xgh.veterinary.query;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "veterinary")
public final class Veterinary {
	@Id
    @Column(name = "id")
	private UUID id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "crmv")
	private String crmv;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "birth_date")
	private Date birthDate;
	
	@JsonIgnore
    @Column(name = "deleted")
    private Boolean deleted = false;

	protected Veterinary() {}

	public Veterinary(UUID id, String name, String phone, String crmv, String email, Date birthDate, Boolean deleted) {
		this.id = id;
		this.name = name;
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
