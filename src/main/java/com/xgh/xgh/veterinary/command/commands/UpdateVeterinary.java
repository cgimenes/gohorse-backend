package com.xgh.xgh.veterinary.command.commands;

import java.util.Date;

import com.xgh.buildingblocks.Command;
import com.xgh.valueobjects.Address;
import com.xgh.valueobjects.Crmv;
import com.xgh.valueobjects.Email;
import com.xgh.valueobjects.Name;
import com.xgh.valueobjects.Phone;
import com.xgh.xgh.veterinary.command.VeterinaryId;

public class UpdateVeterinary implements Command {
	private static final long serialVersionUID = -8599483144628000021L;
	
	private final VeterinaryId id;
	private final Name name;
	private final Address address;
	private final Phone phone;
	private final Crmv crmv;
	private final Email email;
	private final Date birthDate;

	protected UpdateVeterinary() {
		this.id = null;
		this.name = null;
		this.address = null;
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

	public Address getAddress() {
		return this.address;
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