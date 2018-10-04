package com.xgh.model.command.operational.appointment.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.model.command.operational.appointment.AppointmentId;

public class AppointmentWasFinished extends EntityEvent<AppointmentId> {
    public AppointmentWasFinished() {
    }

    public AppointmentWasFinished(AppointmentId id, EntityVersion version) {
        super(id, version);
    }
}
