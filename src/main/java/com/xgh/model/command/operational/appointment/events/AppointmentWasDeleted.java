package com.xgh.model.command.operational.appointment.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.model.command.operational.appointment.AppointmentId;

public class AppointmentWasDeleted extends EntityEvent<AppointmentId> {
    protected AppointmentWasDeleted() {
    }

    public AppointmentWasDeleted(AppointmentId id, EntityVersion version) {
        super(id, version);
    }
}
