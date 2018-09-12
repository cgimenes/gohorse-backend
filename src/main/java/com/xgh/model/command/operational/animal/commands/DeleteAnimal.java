package com.xgh.model.command.operational.animal.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.operational.animal.AnimalId;

public class DeleteAnimal implements Command {
    private final AnimalId id;

    protected DeleteAnimal() {
        this.id = null;
    }

    @Override
    public AnimalId getId() {
        return id;
    }
}
