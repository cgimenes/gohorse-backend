package com.xgh.model.command.veterinary.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.veterinary.VeterinaryId;
import com.xgh.model.command.valueobjects.Address;
import com.xgh.model.command.valueobjects.Crmv;
import com.xgh.model.command.valueobjects.Date;
import com.xgh.model.command.valueobjects.Email;
import com.xgh.model.command.valueobjects.Name;
import com.xgh.model.command.valueobjects.Phone;

public class RegisterVeterinary implements Command {
	private static final long serialVersionUID = -129463298020793445L;

	private final VeterinaryId id;
	private final Name name;
	private final Address address;
	private final Phone phone;
	private final Crmv crmv;
	private final Email email;
	private final Date birthDate;

	protected RegisterVeterinary() {
		this.id = new VeterinaryId();
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
