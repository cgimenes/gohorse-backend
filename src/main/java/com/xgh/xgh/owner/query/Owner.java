package com.xgh.xgh.owner.query;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "owner")
public final class Owner {
	@Column(name = "name")
	private String name;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "cpf")
	private String cpf;
	
	@Column(name = "birth_date")
	private Date birthDate;
	
	@Id
	@Column(name = "id")
	private UUID id;
	
	@JsonIgnore
	@Column(name = "deleted")
	private Boolean deleted = false; 
	
	public Owner(UUID id, String name, String cpf, String phone, Date birthDate, Boolean deleted) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.phone = phone;
		this.birthDate = birthDate;
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
	
	public Date getBirthDate() {
		return birthDate;
	}
	
	public Boolean isDeleted() {
		return deleted;
	}
}
