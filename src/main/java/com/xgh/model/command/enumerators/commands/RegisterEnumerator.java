package com.xgh.model.command.enumerators.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.enumerators.EnumeratorId;
import com.xgh.model.command.valueobjects.Description;
import com.xgh.model.command.valueobjects.Name;

public final class RegisterEnumerator implements Command {
    private final EnumeratorId id;
    private final Name kind;
    private final Description name;

    protected RegisterEnumerator() {
        this.id = new EnumeratorId();
        this.kind = null;
        this.name = null;
    }

    @Override
    public EnumeratorId getId() {
        return this.id;
    }

    public Name getKind() {
        return this.kind;
    }

    public Description getName() {
        return this.name;
    }
}
