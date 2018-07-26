package com.xgh.model.command.additionalregister.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.Event;
import com.xgh.model.command.additionalregister.AdditionalRegisterId;
import com.xgh.model.command.valueobjects.Name;
import com.xgh.model.command.valueobjects.Description;

public class AdditionalRegisterWasRegistered extends Event<AdditionalRegisterId> {
    private Name type;
    private Description value;

    protected AdditionalRegisterWasRegistered() {
    }

    public AdditionalRegisterWasRegistered(AdditionalRegisterId id, Name type, Description value, EntityVersion version) {
        super(id, version);
        this.type = type;
        this.value = value;
    }

    public Name getType() {
        return type;
    }

    public Description getValue() {
        return value;
    }

}
