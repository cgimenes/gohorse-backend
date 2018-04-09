package com.xgh.xgh.owner.command;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.xgh.buildingblocks.AggregateRoot;
import com.xgh.exceptions.NullMandatoryArgumentException;
import com.xgh.valueobjects.Cpf;
import com.xgh.valueobjects.Name;
import com.xgh.valueobjects.Phone;
import com.xgh.xgh.owner.command.events.OwnerWasDeleted;
import com.xgh.xgh.owner.command.events.OwnerWasRegistered;
import com.xgh.xgh.owner.command.events.OwnerWasUpdated;

@Entity
@Table(name = "owner")
public final class Owner extends AggregateRoot<OwnerId>{
	@Embedded
	@AttributeOverride(name = "value", column = @Column(name = "name"))
	private Name name;
	
	@Embedded
	@AttributeOverride(name = "value", column = @Column(name = "phone"))
	private Phone phone;
	
	@Embedded
	@AttributeOverride(name = "value", column = @Column(name = "cpf"))
	private Cpf cpf;
	
	@Column(name = "birth_date")
	private Date birthDate;
	
	public void register(OwnerId id, Name name, Phone phone, Cpf cpf, Date birthDate) {
		if (name == null) {
    		throw new NullMandatoryArgumentException("nome");
    	}
    	
    	if (phone == null) {
    		throw new NullMandatoryArgumentException("telefone");
    	}
    	
    	if (cpf == null) {
    		throw new NullMandatoryArgumentException("cpf");
    	}
    	
    	if (birthDate == null) {
    		throw new NullMandatoryArgumentException("data de nascimento");
    	}
    	
		recordAndApply(new OwnerWasRegistered(id, name, phone, cpf, birthDate, this.nextVersion()));
	}
	
	public void update(Name name, Phone phone, Cpf cpf, Date birthDate) {
		recordAndApply(new OwnerWasUpdated(this.id, name, phone, cpf, birthDate, this.nextVersion()));
	}
	
	public void delete() {
		recordAndApply(new OwnerWasDeleted(this.id, this.nextVersion()));
	}
	
	protected void when(OwnerWasRegistered event) {
		this.id = event.getEntityId();
		this.name = event.getName();
		this.cpf = event.getCpf();
		this.phone = event.getPhone();
		this.birthDate = event.getBirthDate();
	}
	
	protected void when(OwnerWasUpdated event) {
		this.name = event.getName();
		this.cpf = event.getCpf();
		this.phone = event.getPhone();
		this.birthDate = event.getBirthDate();
	}
	
	protected void when(OwnerWasDeleted event) {
		this.markDeleted();
	}
	public Owner() {
		super();
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
