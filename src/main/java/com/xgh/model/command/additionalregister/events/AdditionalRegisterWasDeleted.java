package com.xgh.model.command.additionalregister.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.Event;
import com.xgh.model.command.additionalregister.AdditionalRegisterId;

public class AdditionalRegisterWasDeleted extends Event<AdditionalRegisterId> {
    protected AdditionalRegisterWasDeleted() {
    }

    public AdditionalRegisterWasDeleted(AdditionalRegisterId id, EntityVersion version) {
        super(id, version);
    }
}
