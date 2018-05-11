package com.xgh.model.command.animal.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.animal.AnimalId;

public class DeleteAnimal implements Command{
	private static final long serialVersionUID = 3024239451469312194L;
	private final AnimalId id;
	
	protected DeleteAnimal() {
		this.id = null;
	}
	
	public AnimalId getId() {
		return id;
	}
}
