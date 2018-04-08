package com.xgh.xgh.veterinary.command.events;

import java.util.Date;

import com.xgh.buildingblocks.Event;
import com.xgh.valueobjects.Address;
import com.xgh.valueobjects.Crmv;
import com.xgh.valueobjects.EntityVersion;
import com.xgh.valueobjects.Email;
import com.xgh.valueobjects.Name;
import com.xgh.valueobjects.Phone;
import com.xgh.xgh.veterinary.command.VeterinaryId;

public class VeterinaryWasUpdated extends Event<VeterinaryId> {
	private static final long serialVersionUID = -4536989718000692293L;

	private Name name;
	private Address address;
	private Phone phone;
	private Crmv crmv;
	private Email email;
	private Date birthDate;

	protected VeterinaryWasUpdated() {}
	
	public VeterinaryWasUpdated(VeterinaryId id, Name name, Address address, Phone phone, Crmv crmv, Email email, Date birthDate,
			EntityVersion version) {
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
