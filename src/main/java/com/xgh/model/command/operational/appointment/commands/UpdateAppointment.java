package com.xgh.model.command.operational.appointment.commands;

import com.xgh.buildingblocks.command.EntityCommand;
import com.xgh.model.command.operational.appointment.AppointmentId;
import com.xgh.model.command.operational.appointment.AppointmentPlace;
import com.xgh.model.command.operational.valueobjects.Address;
import com.xgh.model.command.operational.veterinary.VeterinaryId;
import java.time.LocalDateTime;

public class UpdateAppointment implements EntityCommand {
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
