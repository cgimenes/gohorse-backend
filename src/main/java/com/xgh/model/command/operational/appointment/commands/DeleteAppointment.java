package com.xgh.model.command.operational.appointment.commands;

import com.xgh.buildingblocks.command.EntityCommand;
import com.xgh.model.command.operational.appointment.AppointmentId;

public class DeleteAppointment implements EntityCommand {
    private final AppointmentId id;

    protected DeleteAppointment() {
        this.id = null;
    }

    @Override
    public AppointmentId getId() {
        return id;
    }
}
