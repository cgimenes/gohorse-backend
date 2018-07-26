package com.xgh.model.command.additionalregister;

import com.xgh.buildingblocks.entity.AggregateRoot;
import com.xgh.exceptions.NullMandatoryArgumentException;
import com.xgh.model.command.additionalregister.events.AdditionalRegisterWasDeleted;
import com.xgh.model.command.additionalregister.events.AdditionalRegisterWasRegistered;
import com.xgh.model.command.additionalregister.events.AdditionalRegisterWasUpdated;
import com.xgh.model.command.valueobjects.Name;
import com.xgh.model.command.valueobjects.Description;

public final class AdditionalRegister extends AggregateRoot<AdditionalRegisterId> {
    private Name type;
    private Description value;

    public AdditionalRegister() {
        super();
    }

    public void register(AdditionalRegisterId id, Name type, Description value) {
        if (id == null) {
            throw new NullMandatoryArgumentException("id");
        }

        if (type == null) {
            throw new NullMandatoryArgumentException("type");
        }

        if (value == null) {
            throw new NullMandatoryArgumentException("value");
        }

        recordAndApply(new AdditionalRegisterWasRegistered(id, type, value, this.nextVersion()));
    }

    public void update(Name type, Description value) {
        recordAndApply(new AdditionalRegisterWasUpdated(this.id, type, value, this.nextVersion()));
    }

    public void delete() {
        recordAndApply(new AdditionalRegisterWasDeleted(this.id, this.nextVersion()));
    }

    protected void when(AdditionalRegisterWasRegistered event) {
        this.id = event.getEntityId();
        this.type = event.getType();
        this.value = event.getValue();
    }

    protected void when(AdditionalRegisterWasUpdated event) {
    	this.type = event.getRegisterType();
        this.value = event.getValue();
    }

    protected void when(AdditionalRegisterWasDeleted event) {
        this.markDeleted();
    }

    public Name getRegisterType() {
        return this.type;
    }

    public Description getValue() {
        return this.value;
    }
}
