package com.xgh.model.command.enumerators.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.Event;
import com.xgh.model.command.valueobjects.Name;
import com.xgh.model.command.enumerators.EnumeratorId;
import com.xgh.model.command.valueobjects.Description;

public class EnumeratorWasRegistered extends Event<EnumeratorId> {
    private Name group;
    private Description name;

    protected EnumeratorWasRegistered() {
    }

    public EnumeratorWasRegistered(EnumeratorId id, Name group, Description name, EntityVersion version) {
        super(id, version);
        this.group = group;
        this.name = name;
    }

    public Name getGroup() {
        return group;
    }

    public Description getName() {
        return name;
    }

}
