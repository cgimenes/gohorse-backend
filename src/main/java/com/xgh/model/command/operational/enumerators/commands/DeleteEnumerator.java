package com.xgh.model.command.operational.enumerators.commands;

import com.xgh.buildingblocks.command.EntityCommand;
import com.xgh.model.command.operational.enumerators.EnumeratorId;

public class DeleteEnumerator implements EntityCommand {
    private final EnumeratorId id;

    protected DeleteEnumerator() {
        this.id = null;
    }

    @Override
    public EnumeratorId getId() {
        return id;
    }
}
