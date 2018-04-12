package com.xgh.model.owner.command.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.owner.command.OwnerId;
import com.xgh.model.valueobjects.command.Address;
import com.xgh.model.valueobjects.command.Cpf;
import com.xgh.model.valueobjects.command.Date;
import com.xgh.model.valueobjects.command.Name;
import com.xgh.model.valueobjects.command.Phone;

public final class RegisterOwner implements Command {
	private static final long serialVersionUID = 7829193234187879671L;
	
	private final OwnerId id;
	private final Name name;
	private final Cpf cpf;
	private final Phone phone;
	private final Date birthDate;
	private final Address address;
	
	public RegisterOwner() {
	 // Gera um id para o Proprietário, caso o mesmo não tenha passado pela API ainda
		this.id = new OwnerId();
		this.name = null;
		this.cpf = null;
		this.phone = null;
		this.birthDate = null;
		this.address = null;
	}
	
	public OwnerId getId() {
		return this.id;
	}
	
	public Name getName() {
		return this.name;
	}
	
	public Cpf getCpf() {
		return this.cpf;
	}
	
	public Phone getPhone() {
		return this.phone;
	}
	
	public Date getBirthDate() {
		return this.birthDate;
	}
	
	public Address getAddress() {
		return this.address;
	}
	
}
