package com.xgh.model.command.operational.appointment.commands;

import com.xgh.buildingblocks.command.EntityCommand;
import com.xgh.model.command.operational.appointment.AppointmentId;
import com.xgh.model.command.operational.appointment.AppointmentPlace;
import com.xgh.model.command.operational.valueobjects.Address;
import com.xgh.model.command.operational.veterinary.VeterinaryId;
import java.time.LocalDateTime;

public class UpdateAppointment implements EntityCommand {
    private AppointmentId id;
    private VeterinaryId veterinary;
    private LocalDateTime dateTime;
    private AppointmentPlace place;
    private Address address;

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
