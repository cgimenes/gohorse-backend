package com.xgh.xgh.owner.command.commands;

import java.util.Date;

import com.xgh.buildingblocks.Command;
import com.xgh.valueobjects.Cpf;
import com.xgh.valueobjects.Name;
import com.xgh.valueobjects.Phone;
import com.xgh.xgh.owner.command.OwnerId;

public final class RegisterOwner extends Command {
	private static final long serialVersionUID = 7829193234187879671L;
	
	private final OwnerId id;
	private final Name name;
	private final Cpf cpf;
	private final Phone phone;
	private final Date birthDate;
	
	public RegisterOwner() {
	 // Gera um id para o Proprietário, caso o mesmo não tenha passado pela API ainda
		this.id = new OwnerId();
		this.name = null;
		this.cpf = null;
		this.phone = null;
		this.birthDate = null;
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
	
}
