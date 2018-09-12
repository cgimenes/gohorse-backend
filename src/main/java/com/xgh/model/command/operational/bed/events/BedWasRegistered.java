package com.xgh.model.command.operational.bed.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.Event;
import com.xgh.model.command.operational.bed.BedId;
import com.xgh.model.command.operational.valueobjects.Code;

public class BedWasRegistered extends Event<BedId> {
    private Code code;

    protected BedWasRegistered() {
    }

    public BedWasRegistered(BedId id, Code code, EntityVersion version) {
        super(id, version);
        this.code = code;
    }

    public Code getCode() {
        return this.code;
    }
}
