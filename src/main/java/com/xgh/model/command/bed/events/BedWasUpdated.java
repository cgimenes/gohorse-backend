package com.xgh.model.command.bed.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.Event;
import com.xgh.model.command.bed.BedId;
import com.xgh.model.command.valueobjects.Code;

public class BedWasUpdated extends Event<BedId> {
    private Code code;

    protected BedWasUpdated() {
    }

    public BedWasUpdated(BedId id, Code code, EntityVersion version) {
        super(id, version);
        this.code = code;
    }

    public Code getCode() {
        return this.code;
    }
}
