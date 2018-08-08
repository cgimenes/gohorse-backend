package com.xgh.model.command.enumerators.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.enumerators.EnumeratorId;

public class DeleteEnumerator implements Command {
    private final EnumeratorId id;

    protected DeleteEnumerator() {
        this.id = null;
    }

    @Override
    public EnumeratorId getId() {
        return id;
    }
}
