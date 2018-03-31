package com.xgh.xgh.veterinary.commandmodel.commands;

import java.sql.Date;

import com.xgh.buildingblocks.Command;
import com.xgh.valueobjects.Crmv;
import com.xgh.valueobjects.Mail;
import com.xgh.valueobjects.Name;
import com.xgh.valueobjects.Phone;
import com.xgh.xgh.veterinary.commandmodel.VeterinaryId;

public class UpdateVeterinary extends Command{
	private static final long serialVersionUID = -8599483144628000021L;
	
	private final VeterinaryId id;
	private final Name name;
	private final Phone phone;
	private final Crmv crmv;
	private final Mail mail;
	private final Date birthDate;
	private final boolean active;

	public UpdateVeterinary() {
		this.id = null;
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