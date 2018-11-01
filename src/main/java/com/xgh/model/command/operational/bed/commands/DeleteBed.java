package com.xgh.model.command.operational.bed.commands;

import com.xgh.buildingblocks.command.EntityCommand;
import com.xgh.model.command.operational.bed.BedId;

public class DeleteBed implements EntityCommand {
    private final BedId id;

    protected DeleteBed() {
        this.id = null;
    }

    @Override
    public BedId getId() {
        return this.id;
    }
}
