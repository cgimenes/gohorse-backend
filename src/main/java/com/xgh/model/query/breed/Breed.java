package com.xgh.model.query.breed;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.xgh.model.command.valueobjects.Name;

@Entity
@Table(name="breed")
public class Breed {
	@Id
	@GeneratedValue
	private UUID id;
	
	private Name name;
	
	protected Breed() {}

	public Breed(Name name) {
		this.name = name;
	}

	public UUID getId() {
		return id;
	}

	public Name getName() {
		return name;
	}
	

}
