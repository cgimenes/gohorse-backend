package com.xgh.model.command.veterinary.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.veterinary.VeterinaryId;

public class DeleteVeterinary implements Command {
    private final VeterinaryId id;

    protected DeleteVeterinary() {
        this.id = null;
    }

    @Override
    public VeterinaryId getId() {
        return this.id;
    }
}
