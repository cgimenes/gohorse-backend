package com.xgh.model.command.enumerators.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.valueobjects.Name;
import com.xgh.model.command.enumerators.EnumeratorId;
import com.xgh.model.command.valueobjects.Description;

public final class UpdateEnumerator implements Command {
    private final EnumeratorId id;
    private final Name group;
    private final Description name;

    protected UpdateEnumerator() {
        this.id = null;
        this.group = null;
        this.name = null;
    }

    @Override
    public EnumeratorId getId() {
        return id;
    }

    public Name getGroup() {
        return group;
    }

    public Description getName() {
        return name;
    }
}
