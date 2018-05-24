package com.xgh.model.query.specie;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="specie")
public class Specie {
	@Id
	@GeneratedValue
	private UUID id;
	
	private String name;
	
	protected Specie() {}

	public Specie(String name) {
		this.name = name;
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
