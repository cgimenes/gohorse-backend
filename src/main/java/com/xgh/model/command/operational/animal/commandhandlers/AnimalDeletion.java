package com.xgh.model.command.operational.animal.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.operational.animal.Animal;
import com.xgh.model.command.operational.animal.commands.DeleteAnimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnimalDeletion implements CommandHandler<DeleteAnimal> {
    private final EventStore repository;

    @Autowired
    public AnimalDeletion(EventStore repository) {
        this.repository = repository;
    }

    @Override
    public void execute(DeleteAnimal command) {
        Animal animal = repository.pull(Animal.class, command.getId());
        animal.delete();
        repository.push(animal);
    }
}
