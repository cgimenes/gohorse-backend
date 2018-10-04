package com.xgh.model.command.operational.enumerators.commands;

import com.xgh.buildingblocks.command.EntityCommand;
import com.xgh.model.command.operational.enumerators.EnumeratorId;
import com.xgh.model.command.operational.valueobjects.Name;
import com.xgh.model.command.operational.valueobjects.Description;

public final class UpdateEnumerator implements EntityCommand {
    private final EnumeratorId id;
    private final Name kind;
    private final Description name;

    protected UpdateEnumerator() {
        this.id = null;
        this.kind = null;
        this.name = null;
    }

    @Override
    public EnumeratorId getId() {
        return id;
    }

    public Name getKind() {
        return kind;
    }

    public Description getName() {
        return name;
    }
}
