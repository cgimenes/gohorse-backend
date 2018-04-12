package com.xgh.model.command.veterinary.events;

import com.xgh.buildingblocks.event.Event;
import com.xgh.model.command.valueobjects.Address;
import com.xgh.model.command.valueobjects.Crmv;
import com.xgh.model.command.valueobjects.Date;
import com.xgh.model.command.valueobjects.Email;
import com.xgh.model.command.valueobjects.EntityVersion;
import com.xgh.model.command.valueobjects.Name;
import com.xgh.model.command.valueobjects.Phone;
import com.xgh.model.command.veterinary.VeterinaryId;

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
