package com.xgh.model.command.appointment.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.Event;
import com.xgh.model.command.appointment.AppointmentId;

public class AppointmentWasDeleted extends Event<AppointmentId> {
    protected AppointmentWasDeleted() {
    }

    public AppointmentWasDeleted(AppointmentId id, EntityVersion version) {
        super(id, version);
    }
}
