package com.xgh.model.command.operational.owner.commands;

import com.xgh.buildingblocks.command.EntityCommand;
import com.xgh.model.command.operational.owner.OwnerId;

public class DeleteOwner implements EntityCommand {
    private final OwnerId id;

    protected DeleteOwner() {
        this.id = null;
    }

    @Override
    public OwnerId getId() {
        return id;
    }
}
