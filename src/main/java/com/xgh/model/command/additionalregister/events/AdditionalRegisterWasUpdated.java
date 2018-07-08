package com.xgh.model.command.additionalregister.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.Event;
import com.xgh.model.command.additionalregister.AdditionalRegisterId;
import com.xgh.model.command.valueobjects.Name;
import com.xgh.model.command.valueobjects.Description;

public class AdditionalRegisterWasUpdated extends Event<AdditionalRegisterId> {
    private Name type;
    private Description value;

    protected AdditionalRegisterWasUpdated() {
    }

    public AdditionalRegisterWasUpdated(AdditionalRegisterId id, Name type, Description value, EntityVersion version) {
        super(id, version);
        this.type = type;
        this.value = value;
    }

    public Name getRegisterType() {
        return type;
    }

    public Description getValue() {
        return value;
    }
}
