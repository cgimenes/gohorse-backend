package com.xgh.xgh.veterinary.commandmodel;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.xgh.buildingblocks.DomainEntity;
import com.xgh.valueobjects.Name;
import com.xgh.valueobjects.Phone;
import com.xgh.xgh.veterinary.commandmodel.events.VeterinaryWasRegistered;
import com.xgh.xgh.veterinary.commandmodel.events.VeterinaryWasUpdated;
import com.xgh.valueobjects.Crmv;
import com.xgh.valueobjects.Mail;

@Entity
@Table(name = "veterinary")
public class Veterinary extends DomainEntity<VeterinaryId> {
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
	@AttributeOverride(name = "value", column = @Column(name = "mail"))
	private Mail mail;

	@Column(name = "birth_date")
	private Date birthDate;

	@Column(name = "active")
	private boolean active;

	public Veterinary() {
		
	}

	public void register(VeterinaryId id, Name name, Phone phone, Crmv crmv, Mail mail, Date birthDate,
			boolean active) {
		// TODO: Validar campos obrigatórios
		recordAndApply(new VeterinaryWasRegistered(id, name, phone, crmv, mail, birthDate, active, this.nextVersion()));
	}

	public void update(Name name, Phone phone, Crmv crmv, Mail mail, Date birthDate, boolean active) {
		recordAndApply(
				new VeterinaryWasUpdated(this.id, name, phone, crmv, mail, birthDate, active, this.nextVersion()));
	}
	
	protected void when(VeterinaryWasRegistered event) {
		this.id = event.getEntityId();
		this.name = event.getName();
		this.phone = event.getPhone();
		this.crmv = event.getCrmv();
		this.mail = event.getMail();
		this.birthDate = event.getBirthDate();
		this.active = event.isActive();
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

	public Mail getMail() {
		return mail;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public boolean isActive() {
		return active;
	}

}
