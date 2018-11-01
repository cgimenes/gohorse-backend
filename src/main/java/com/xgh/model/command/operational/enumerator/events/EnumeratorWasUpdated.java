package com.xgh.model.command.operational.enumerator.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.model.command.operational.valueobjects.Name;
import com.xgh.model.command.operational.enumerator.EnumeratorId;
import com.xgh.model.command.operational.valueobjects.Description;

public class EnumeratorWasUpdated extends EntityEvent<EnumeratorId> {
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
