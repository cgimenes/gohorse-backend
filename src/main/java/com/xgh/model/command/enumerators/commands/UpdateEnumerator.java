package com.xgh.model.command.enumerators.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.valueobjects.Name;
import com.xgh.model.command.enumerators.EnumeratorId;
import com.xgh.model.command.valueobjects.Description;

public final class UpdateEnumerator implements Command {
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
