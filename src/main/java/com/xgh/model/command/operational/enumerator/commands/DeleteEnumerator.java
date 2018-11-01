package com.xgh.model.command.operational.enumerator.commands;

import com.xgh.buildingblocks.command.EntityCommand;
import com.xgh.model.command.operational.enumerator.EnumeratorId;

public class DeleteEnumerator implements EntityCommand {
    private final EnumeratorId id;

    protected DeleteEnumerator() {
        this.id = null;
    }

    public EnumeratorId getId() {
        return id;
    }
}
