package com.xgh.model.command.operational.appointment.commands;

import com.xgh.buildingblocks.command.EntityCommand;
import com.xgh.model.command.operational.appointment.AppointmentId;
import java.math.BigDecimal;

public class FinishAppointment implements EntityCommand {
    private final AppointmentId id;
    private final BigDecimal price;

    protected FinishAppointment() {
        this.id = null;
        this.price = null;
    }

    public FinishAppointment(AppointmentId id, BigDecimal price) {
        this.id = id;
        this.price = price;
    }

    @Override
    public AppointmentId getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
