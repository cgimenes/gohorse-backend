package com.xgh.model.command.operational.appointment.commands;

import com.xgh.buildingblocks.command.EntityCommand;
import com.xgh.model.command.operational.appointment.AppointmentId;

public class CancelAppointment implements EntityCommand {
    private final AppointmentId id;

    protected CancelAppointment() {
        this.id = null;
    }

    @Override
    public AppointmentId getId() {
        return id;
    }
}
