package com.xgh.model.command.operational.bed;

import com.xgh.buildingblocks.entity.AggregateRoot;
import com.xgh.exceptions.NullMandatoryArgumentException;
import com.xgh.model.command.operational.bed.events.BedWasDeleted;
import com.xgh.model.command.operational.bed.events.BedWasRegistered;
import com.xgh.model.command.operational.bed.events.BedWasUpdated;
import com.xgh.model.command.operational.valueobjects.Code;

public class Bed extends AggregateRoot<BedId> {
    private Code code;

    public void register(BedId id, Code code) {
        if (id == null) {
            throw new NullMandatoryArgumentException("id");
        }

        if (code == null) {
            throw new NullMandatoryArgumentException("code");
        }

        recordAndApply(new BedWasRegistered(id, code, this.nextVersion()));
    }

    public void update(Code code) {
        recordAndApply(new BedWasUpdated(this.id, code, this.nextVersion()));
    }

    public void delete() {
        recordAndApply(new BedWasDeleted(this.id, this.nextVersion()));
    }

    protected void when(BedWasRegistered event) {
        this.code = event.getCode();
    }

    protected void when(BedWasUpdated event) {
        this.code = event.getCode();
    }

    protected void when(BedWasDeleted event) {
        this.markDeleted();
    }

    public Code getCode() {
        return code;
    }
}
