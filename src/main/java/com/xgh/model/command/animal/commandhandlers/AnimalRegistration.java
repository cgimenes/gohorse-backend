package com.xgh.model.command.animal.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.animal.Animal;
import com.xgh.model.command.animal.commands.RegisterAnimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnimalRegistration implements CommandHandler<RegisterAnimal> {
    private EventStore repository;

    @Autowired
    public AnimalRegistration(EventStore repository) {
        this.repository = repository;
    }

    @Override
    public void execute(RegisterAnimal command) {
        Animal animal = new Animal();
        animal.register(command.getId(), command.getName(), command.getOwner(), command.getBreed(), command.getSpecie(), command.getSex(), command.getBirthDate(), command.getWeight(), command.isCastrated());
        repository.push(animal);
    }
}
