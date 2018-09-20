package com.xgh.model.command.appointment.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.appointment.AppointmentId;
import com.xgh.model.command.appointment.AppointmentPlace;
import com.xgh.model.command.valueobjects.Address;
import com.xgh.model.command.veterinary.VeterinaryId;
import java.time.LocalDateTime;

public class UpdateAppointment implements Command {
    private final AppointmentId id;
    private final VeterinaryId veterinary;
    private final LocalDateTime dateTime;
    private final AppointmentPlace place;
    private final Address address;

    protected UpdateAppointment() {
        this.id = new AppointmentId();
        veterinary = null;
        dateTime = null;
        place = null;
        address = null;
    }

    @Override
    public AppointmentId getId() {
        return id;
    }

    public VeterinaryId getVeterinary() {
        return veterinary;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public AppointmentPlace getPlace() {
        return place;
    }

    public Address getAddress() {
        return address;
    }
}
