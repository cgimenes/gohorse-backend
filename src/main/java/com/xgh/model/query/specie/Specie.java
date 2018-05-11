package com.xgh.model.query.specie;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.xgh.model.command.valueobjects.Name;

@Entity
@Table(name="specie")
public class Specie {
	@Id
	@GeneratedValue
	private UUID id;
	
	private Name name;
	
	protected Specie() {}

	public Specie(Name name) {
		this.name = name;
	}

	public UUID getId() {
		return id;
	}

	public Name getName() {
		return name;
	}
}
