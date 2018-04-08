package com.xgh.xgh.veterinary.command.commands;

import java.util.Date;

import com.xgh.buildingblocks.Command;
import com.xgh.valueobjects.Crmv;
import com.xgh.valueobjects.Email;
import com.xgh.valueobjects.Name;
import com.xgh.valueobjects.Phone;
import com.xgh.xgh.veterinary.command.VeterinaryId;

public class RegisterVeterinary extends Command {
	private static final long serialVersionUID = -129463298020793445L;

	private final VeterinaryId id;
	private final Name name;
	private final Phone phone;
	private final Crmv crmv;
	private final Email email;
	private final Date birthDate;

	protected RegisterVeterinary() {
		this.id = new VeterinaryId();
		this.name = null;
		this.phone = null;
		this.crmv = null;
		this.email = null;
		this.birthDate = null;
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

	public Email getEmail() {
		return this.email;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}
	
}
