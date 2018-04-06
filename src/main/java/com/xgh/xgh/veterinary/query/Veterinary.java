package com.xgh.xgh.veterinary.query;

import java.util.UUID;

public final class Veterinary {
	private UUID id;
	private String name;
	private String phone;
	private String crmv;
	private String email;
	private String birthDate;

	public Veterinary(UUID id, String name, String phone, String crmv, String email, String birthDate) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.crmv = crmv;
		this.email = email;
		this.birthDate = birthDate;
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

	public String getBirthDate() {
		return this.birthDate;
	}

}
