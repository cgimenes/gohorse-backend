package com.xgh.xgh.owner.commandmodel.events;

import com.xgh.buildingblocks.Event;
import com.xgh.valueobjects.Cpf;
import com.xgh.valueobjects.EntityVersion;
import com.xgh.valueobjects.Name;
import com.xgh.valueobjects.Phone;
import com.xgh.xgh.owner.commandmodel.OwnerId;

public class OwnerWasRegistered extends Event<OwnerId> {
	private static final long serialVersionUID = -3062625947435023015L;
	
	private Name name;
	private Phone phone;
	private Cpf cpf;
	
	public OwnerWasRegistered() {	
	}

	public OwnerWasRegistered(OwnerId id, Name name, Phone phone, Cpf cpf, EntityVersion version) {
		super(id, version);
		this.name = name;
		this.phone = phone;
		this.cpf = cpf;
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
	
}
