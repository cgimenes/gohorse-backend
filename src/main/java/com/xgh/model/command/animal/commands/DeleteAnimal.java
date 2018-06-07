package com.xgh.model.command.animal.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.animal.AnimalId;

public class DeleteAnimal implements Command {
    private final AnimalId id;

    protected DeleteAnimal() {
        this.id = null;
    }

    public AnimalId getId() {
        return id;
    }
}
