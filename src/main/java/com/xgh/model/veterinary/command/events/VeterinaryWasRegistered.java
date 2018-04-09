package com.xgh.model.veterinary.command.events;

import java.util.Date;

import com.xgh.buildingblocks.event.Event;
import com.xgh.model.valueobjects.command.Address;
import com.xgh.model.valueobjects.command.Crmv;
import com.xgh.model.valueobjects.command.Email;
import com.xgh.model.valueobjects.command.EntityVersion;
import com.xgh.model.valueobjects.command.Name;
import com.xgh.model.valueobjects.command.Phone;
import com.xgh.model.veterinary.command.VeterinaryId;

public class VeterinaryWasRegistered extends Event<VeterinaryId> {
	private static final long serialVersionUID = -6601994182805982237L;

	private Name name;
	private Address address;
	private Phone phone;
	private Crmv crmv;
	private Email email;
	private Date birthDate;

	protected VeterinaryWasRegistered() {}

	public VeterinaryWasRegistered(VeterinaryId id, Name name, Address address, Phone phone, 
			Crmv crmv, Email email, Date birthDate, EntityVersion version) {
		super(id, version);
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.crmv = crmv;
		this.email = email;
		this.birthDate = birthDate;
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
