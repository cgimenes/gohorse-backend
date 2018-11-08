package com.xgh.model.command.operational.veterinary.commands;

import com.xgh.buildingblocks.command.EntityCommand;
import com.xgh.model.command.operational.veterinary.VeterinaryId;

public class DeleteVeterinary implements EntityCommand {
    private final VeterinaryId id;

    protected DeleteVeterinary() {
        this.id = null;
    }

    @Override
    public VeterinaryId getId() {
        return this.id;
    }
}
