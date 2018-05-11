package com.xgh.model.command.animal.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.animal.Animal;
import com.xgh.model.command.animal.commands.UpdateAnimal;

public class AnimalUpdate implements CommandHandler<UpdateAnimal> {
	private EventStore repository;
	
	public AnimalUpdate(EventStore repository) {
		this.repository = repository;
	}
	
	@Override
	public void execute(UpdateAnimal command) {
		Animal animal = repository.pull(Animal.class, command.getId());
		animal.update(command.getName(), command.getOwner(), command.getBreed(), command.getSpecie(), command.getSex(), command.getBirthDate(), command.getWeight(), command.isCastrated());
		repository.push(animal);
	}

}
