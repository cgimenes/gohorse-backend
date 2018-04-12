package com.xgh.model.owner.command.events;

import com.xgh.buildingblocks.event.Event;
import com.xgh.model.owner.command.OwnerId;
import com.xgh.model.valueobjects.command.Address;
import com.xgh.model.valueobjects.command.Cpf;
import com.xgh.model.valueobjects.command.Date;
import com.xgh.model.valueobjects.command.EntityVersion;
import com.xgh.model.valueobjects.command.Name;
import com.xgh.model.valueobjects.command.Phone;

public class OwnerWasUpdated extends Event<OwnerId> {
	private static final long serialVersionUID = 9166049599376937830L;
	
	private Name name;
	private Phone phone;
	private Cpf cpf;
	private Date birthDate;
	private Address address;
	
	public OwnerWasUpdated() {
	}
	
	public OwnerWasUpdated(OwnerId id, Name name, Phone phone, Cpf cpf, Date birthDate, Address address, EntityVersion version) {
		super(id, version);
		this.name = name;
		this.phone = phone;
		this.cpf = cpf;
		this.birthDate = birthDate;
		this.address = address;
	}

	public Name getName() {
		return name;
	}

	public Phone getPhone() {
		return phone;
	}

	public Cpf getCpf() {
		return cpf;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}
	
	public Address getAddress() {
		return address;
	}
}
