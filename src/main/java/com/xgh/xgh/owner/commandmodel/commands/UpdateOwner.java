package com.xgh.xgh.owner.commandmodel.commands;

import com.xgh.buildingblocks.Command;
import com.xgh.valueobjects.Cpf;
import com.xgh.valueobjects.Name;
import com.xgh.valueobjects.Phone;
import com.xgh.xgh.owner.commandmodel.OwnerId;

public final class UpdateOwner extends Command {
	private static final long serialVersionUID = -5468719672345592356L;
	
	
	private final OwnerId id;
	private final Name name;
	private final Cpf cpf;
	private final Phone phone;
	
	public UpdateOwner() {
		this.id = null;
		this.name = null;
		this.cpf = null;
		this.phone = null;
	}

	public OwnerId getId() {
		return id;
	}

	public Name getName() {
		return name;
	}

	public Cpf getCpf() {
		return cpf;
	}

	public Phone getPhone() {
		return phone;
	}
	
}
