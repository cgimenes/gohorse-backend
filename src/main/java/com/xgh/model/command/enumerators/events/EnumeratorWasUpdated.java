package com.xgh.model.command.enumerators.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.Event;
import com.xgh.model.command.valueobjects.Name;
import com.xgh.model.command.enumerators.EnumeratorId;
import com.xgh.model.command.valueobjects.Description;

public class EnumeratorWasUpdated extends Event<EnumeratorId> {
    private Name kind;
    private Description name;

    protected EnumeratorWasUpdated() {
    }

    public EnumeratorWasUpdated(EnumeratorId id, Name kind, Description name, EntityVersion version) {
        super(id, version);
        this.kind = kind;
        this.name = name;
    }

    public Name getKind() {
        return kind;
    }

    public Description getName() {
        return name;
    }
}
