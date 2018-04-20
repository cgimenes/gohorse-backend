package com.xgh.model.command.animal;

import com.xgh.buildingblocks.entity.AggregateRoot;
import com.xgh.model.command.valueobjects.Name;

public final class Animal extends AggregateRoot<AnimalId> {
	private static final long serialVersionUID = 3118505285619458826L;

	private Name name;
	private Float weight;
	private boolean castrated;
	
	public Name getName() {
		return name;
	}
	public Float getWeight() {
		return weight;
	}
	public boolean isCastrated() {
		return castrated;
	}
}
