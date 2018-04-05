package com.xgh.xgh.veterinary.query;

import java.util.UUID;

public final class Veterinary {
	private UUID id;
	private String name;
	private String phone;
	private String crmv;
	private String mail;
	private String birthDate;
	private String active;

	public Veterinary(UUID id, String name, String phone, String crmv, String mail, String birthDate, String active) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.crmv = crmv;
		this.mail = mail;
		this.birthDate = birthDate;
		this.active = active;
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

	public String getMail() {
		return this.mail;
	}

	public String getBirthDate() {
		return this.birthDate;
	}

	public String isActive() {
		return this.active;
	}

}
