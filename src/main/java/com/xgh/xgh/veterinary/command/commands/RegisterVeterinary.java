package com.xgh.xgh.veterinary.commandmodel.commands;

import java.sql.Date;

import com.xgh.buildingblocks.Command;
import com.xgh.valueobjects.Crmv;
import com.xgh.valueobjects.Mail;
import com.xgh.valueobjects.Name;
import com.xgh.valueobjects.Phone;
import com.xgh.xgh.veterinary.commandmodel.VeterinaryId;

public class RegisterVeterinary extends Command {
	private static final long serialVersionUID = -129463298020793445L;

	private final VeterinaryId id;
	private final Name name;
	private final Phone phone;
	private final Crmv crmv;
	private final Mail mail;
	private final Date birthDate;
	private final boolean active;

	public RegisterVeterinary() {
		this.id = new VeterinaryId();
		this.name = null;
		this.phone = null;
		this.crmv = null;
		this.mail = null;
		this.birthDate = null;
		this.active = true;		
	}

	public VeterinaryId getId() {
		return this.id;
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
