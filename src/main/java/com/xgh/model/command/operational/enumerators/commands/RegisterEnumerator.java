package com.xgh.model.command.operational.enumerators.commands;

import com.xgh.buildingblocks.command.EntityCommand;
import com.xgh.model.command.operational.enumerators.EnumeratorId;
import com.xgh.model.command.operational.valueobjects.Name;
import com.xgh.model.command.operational.valueobjects.Description;

public final class RegisterEnumerator implements EntityCommand {
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
