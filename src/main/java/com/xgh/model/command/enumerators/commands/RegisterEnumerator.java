package com.xgh.model.command.enumerators.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.enumerators.EnumeratorId;
import com.xgh.model.command.valueobjects.Description;
import com.xgh.model.command.valueobjects.Name;

public final class RegisterEnumerator implements Command {
    private final EnumeratorId id;
    private final Name group;
    private final Description name;

    protected RegisterEnumerator() {
        this.id = new EnumeratorId();
        this.group = null;
        this.name = null;
    }

    @Override
    public EnumeratorId getId() {
        return this.id;
    }

    public Name getGroup() {
        return this.group;
    }

    public Description getName() {
        return this.name;
    }
}
