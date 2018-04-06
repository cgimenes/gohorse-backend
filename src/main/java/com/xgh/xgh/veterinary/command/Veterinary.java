package com.xgh.xgh.veterinary.command;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.xgh.buildingblocks.AggregateRoot;
import com.xgh.exceptions.NullMandatoryArgumentException;
import com.xgh.valueobjects.Name;
import com.xgh.valueobjects.Phone;
import com.xgh.xgh.veterinary.command.events.VeterinaryWasDeleted;
import com.xgh.xgh.veterinary.command.events.VeterinaryWasRegistered;
import com.xgh.xgh.veterinary.command.events.VeterinaryWasUpdated;
import com.xgh.valueobjects.Crmv;
import com.xgh.valueobjects.Email;

@Entity
@Table(name = "veterinary")
public class Veterinary extends AggregateRoot<VeterinaryId> {
	@Embedded
	@AttributeOverride(name = "value", column = @Column(name = "name"))
	private Name name;

	@Embedded
	@AttributeOverride(name = "value", column = @Column(name = "phone"))
	private Phone phone;

	@Embedded
	@AttributeOverride(name = "value", column = @Column(name = "crmv"))
	private Crmv crmv;

	@Embedded
	@AttributeOverride(name = "value", column = @Column(name = "email"))
	private Email email;

	@Column(name = "birth_date")
	private Date birthDate;

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
