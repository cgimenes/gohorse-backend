package com.xgh.model.command.operational.enumerators.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.model.command.operational.enumerators.EnumeratorId;
import com.xgh.model.command.operational.valueobjects.Name;
import com.xgh.model.command.operational.valueobjects.Description;

public class EnumeratorWasRegistered extends EntityEvent<EnumeratorId> {
    private Name kind;
    private Description name;

    protected EnumeratorWasRegistered() {
    }

    public EnumeratorWasRegistered(EnumeratorId id, Name kind, Description name, EntityVersion version) {
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
