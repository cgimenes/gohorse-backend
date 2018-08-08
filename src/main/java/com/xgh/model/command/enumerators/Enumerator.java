package com.xgh.model.command.enumerators;

import com.xgh.buildingblocks.entity.AggregateRoot;
import com.xgh.exceptions.NullMandatoryArgumentException;
import com.xgh.model.command.valueobjects.Name;
import com.xgh.model.command.enumerators.events.EnumeratorWasDeleted;
import com.xgh.model.command.enumerators.events.EnumeratorWasRegistered;
import com.xgh.model.command.enumerators.events.EnumeratorWasUpdated;
import com.xgh.model.command.valueobjects.Description;

public final class Enumerator extends AggregateRoot<EnumeratorId> {
    private Name group;
    private Description name;

    public Enumerator() {
        super();
    }

    public void register(EnumeratorId id, Name group, Description name) {
        if (id == null) {
            throw new NullMandatoryArgumentException("id");
        }

        if (group == null) {
            throw new NullMandatoryArgumentException("group");
        }

        if (name == null) {
            throw new NullMandatoryArgumentException("name");
        }

        recordAndApply(new EnumeratorWasRegistered(id, group, name, this.nextVersion()));
    }

    public void update(Name group, Description name) {
        recordAndApply(new EnumeratorWasUpdated(this.id, group, name, this.nextVersion()));
    }

    public void delete() {
        recordAndApply(new EnumeratorWasDeleted(this.id, this.nextVersion()));
    }

    protected void when(EnumeratorWasRegistered event) {
        this.id = event.getEntityId();
        this.group = event.getGroup();
        this.name = event.getName();
    }

    protected void when(EnumeratorWasUpdated event) {
    	this.group = event.getGroup();
        this.name = event.getName();
    }

    protected void when(EnumeratorWasDeleted event) {
        this.markDeleted();
    }

    public Name getGroup() {
        return this.group;
    }

    public Description getName() {
        return this.name;
    }
}
