package com.xgh.model.command.appointment.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.Event;
import com.xgh.model.command.appointment.AppointmentId;

public class AppointmentWasCancelled extends Event<AppointmentId> {
    public AppointmentWasCancelled() {
    }

    public AppointmentWasCancelled(AppointmentId id, EntityVersion version) {
        super(id, version);
    }
}
