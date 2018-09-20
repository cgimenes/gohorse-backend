package com.xgh.model.command.appointment.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.appointment.AppointmentId;

public class DeleteAppointment implements Command {
    private final AppointmentId id;

    protected DeleteAppointment() {
        this.id = null;
    }

    @Override
    public AppointmentId getId() {
        return id;
    }
}
