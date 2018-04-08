package com.xgh.xgh.owner.querymodel;

import java.util.Date;
import java.util.UUID;

public final class Owner {
	private String name;
	private String phone;
	private String cpf;
	private Date birthDate;
	private boolean active;
	private UUID id;
	
	public Owner(UUID id, String name, String cpf, String phone, Date birthDate, boolean active) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.phone = phone;
		this.birthDate = birthDate;
		this.active = active;
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
	
	public boolean getActive() {
		return active;
	}
}
