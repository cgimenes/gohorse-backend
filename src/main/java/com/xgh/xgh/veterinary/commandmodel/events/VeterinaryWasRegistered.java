package com.xgh.xgh.veterinary.commandmodel.events;

import java.sql.Date;

import com.xgh.buildingblocks.Event;
import com.xgh.valueobjects.Crmv;
import com.xgh.valueobjects.EntityVersion;
import com.xgh.valueobjects.Mail;
import com.xgh.valueobjects.Name;
import com.xgh.valueobjects.Phone;
import com.xgh.xgh.veterinary.commandmodel.VeterinaryId;

public class VeterinaryWasRegistered extends Event<VeterinaryId> {
	private static final long serialVersionUID = -6601994182805982237L;

	private Name name;
	private Phone phone;
	private Crmv crmv;
	private Mail mail;
	private Date birthDate;
	private boolean active;

	public VeterinaryWasRegistered(VeterinaryId id, Name name, Phone phone, Crmv crmv, Mail mail, Date birthDate,
			boolean active, EntityVersion version) {
		super(id, version);
		this.name = name;
		this.phone = phone;
		this.crmv = crmv;
		this.mail = mail;
		this.birthDate = birthDate;
		this.active = active;
	}
	
	public Name getName() {
		return this.name;
	}

	public Phone getPhone() {
		return this.phone;
	}

	public Crmv getCrmv() {
		return this.crmv;
	}

	public Mail getMail() {
		return this.mail;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public boolean isActive() {
		return this.active;
	}

}
