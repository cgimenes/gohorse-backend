package com.xgh.xgh.veterinary.command;

import java.util.Date;

import com.xgh.buildingblocks.AggregateRoot;
import com.xgh.exceptions.NullMandatoryArgumentException;
import com.xgh.valueobjects.Name;
import com.xgh.valueobjects.Phone;
import com.xgh.xgh.veterinary.command.events.VeterinaryWasDeleted;
import com.xgh.xgh.veterinary.command.events.VeterinaryWasRegistered;
import com.xgh.xgh.veterinary.command.events.VeterinaryWasUpdated;
import com.xgh.valueobjects.Crmv;
import com.xgh.valueobjects.Email;

public class Veterinary extends AggregateRoot<VeterinaryId> {
	private Name name;
	private Phone phone;
	private Crmv crmv;
	private Email email;
	private Date birthDate;
	
	// TODO adicionar endereço

	public Veterinary() {
		super();
	}

	public void register(VeterinaryId id, Name name, Phone phone, Crmv crmv, Email email, Date birthDate) {
		if (name == null) {
			throw new NullMandatoryArgumentException("nome");
		}

		if (phone == null) {
			throw new NullMandatoryArgumentException("telefone");
		}

		if (crmv == null) {
			throw new NullMandatoryArgumentException("crmv");
		}

		recordAndApply(new VeterinaryWasRegistered(id, name, phone, crmv, email, birthDate, this.nextVersion()));
	}

	public void update(Name name, Phone phone, Crmv crmv, Email email, Date birthDate) {
		recordAndApply(new VeterinaryWasUpdated(this.id, name, phone, crmv, email, birthDate, this.nextVersion()));
	}
	
	public void delete() {
		recordAndApply(new VeterinaryWasDeleted(this.id, this.nextVersion()));
	}

	protected void when(VeterinaryWasRegistered event) {
		this.name = event.getName();
		this.phone = event.getPhone();
		this.crmv = event.getCrmv();
		this.email = event.getEmail();
		this.birthDate = event.getBirthDate();
	}

	protected void when(VeterinaryWasUpdated event) {
		this.name = event.getName();
		this.phone = event.getPhone();
		this.crmv = event.getCrmv();
		this.email = event.getEmail();
		this.birthDate = event.getBirthDate();
	}
	
	protected void when(VeterinaryWasDeleted event) {
		this.markDeleted();
	}

	public Name getName() {
		return name;
	}

	public Phone getPhone() {
		return phone;
	}

	public Crmv getCrmv() {
		return crmv;
	}

	public Email getEmail() {
		return email;
	}

	public Date getBirthDate() {
		return birthDate;
	}
}
