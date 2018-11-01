package com.xgh.model.command.operational.appointment.commands;

import com.xgh.buildingblocks.command.EntityCommand;
import com.xgh.model.command.operational.appointment.AppointmentId;
import com.xgh.model.command.operational.appointment.AppointmentPlace;
import com.xgh.model.command.operational.appointment.AppointmentType;
import com.xgh.model.command.operational.animal.AnimalId;
import com.xgh.model.command.operational.valueobjects.Address;
import com.xgh.model.command.operational.veterinary.VeterinaryId;
import java.time.LocalDateTime;

public final class RegisterAppointment implements EntityCommand {
    private final AppointmentId id;
    private final AnimalId animal;
    private final VeterinaryId veterinary;
    private final LocalDateTime dateTime;
    private final AppointmentType appointmentType;
    private final AppointmentPlace place;
    private final Address address;

    protected RegisterAppointment() {
        this.id = new AppointmentId();
        animal = null;
        veterinary = null;
        dateTime = null;
        appointmentType = null;
        place = null;
        address = null;
    }

    @Override
    public AppointmentId getId() {
        return id;
    }

    public AnimalId getAnimal() {
        return animal;
    }

    public VeterinaryId getVeterinary() {
        return veterinary;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public AppointmentType getAppointmentType() {
        return appointmentType;
    }

    public AppointmentPlace getPlace() {
        return place;
    }

    public Address getAddress() {
        return address;
    }
}
