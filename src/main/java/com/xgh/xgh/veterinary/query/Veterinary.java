package com.xgh.xgh.veterinary.querymodel;

import java.sql.Date;
import java.util.UUID;

import com.xgh.valueobjects.Crmv;
import com.xgh.valueobjects.Mail;
import com.xgh.valueobjects.Name;
import com.xgh.valueobjects.Phone;

public final class Veterinary {
	private UUID id;
	private String name;
	private String phone;
	private String crmv;
	private String mail;
	private Date birthDate;
	private boolean active;

	public Veterinary(UUID id, String name, String phone, String crmv, String mail, Date birthDate, boolean active) {
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

	public Date getBirthDate() {
		return this.birthDate;
	}

	public boolean isActive() {
		return this.active;
	}

}
