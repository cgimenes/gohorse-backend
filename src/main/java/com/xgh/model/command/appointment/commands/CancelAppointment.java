package com.xgh.model.command.appointment.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.appointment.AppointmentId;

public class CancelAppointment implements Command {
    private final AppointmentId id;

    protected CancelAppointment() {
        this.id = null;
    }

    @Override
    public AppointmentId getId() {
        return id;
    }
}
