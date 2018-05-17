package com.xgh.model.command.owner.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.owner.OwnerId;

public class DeleteOwner implements Command {
    private final OwnerId id;

    protected DeleteOwner() {
        this.id = null;
    }

    @Override
    public OwnerId getId() {
        return id;
    }
}
