package com.xgh.model.command.operational.appointment.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.model.command.operational.appointment.AppointmentId;
import com.xgh.model.command.operational.appointment.AppointmentPlace;
import com.xgh.model.command.operational.appointment.AppointmentStatus;
import com.xgh.model.command.operational.appointment.AppointmentType;
import com.xgh.model.command.operational.animal.AnimalId;
import com.xgh.model.command.operational.valueobjects.Address;
import com.xgh.model.command.operational.veterinary.VeterinaryId;
import java.time.LocalDateTime;

public class AppointmentWasRegistered extends EntityEvent<AppointmentId> {
    private AnimalId animal;
    private VeterinaryId veterinary;
    private LocalDateTime dateTime;
    private AppointmentStatus status;
    private AppointmentType appointmentType;
    private AppointmentPlace place;
    private Address address;

    protected AppointmentWasRegistered() {
    }

    public AppointmentWasRegistered(AppointmentId id, AnimalId animal, VeterinaryId veterinary, LocalDateTime dateTime, AppointmentStatus status, AppointmentType appointmentType, AppointmentPlace place, Address address, EntityVersion version) {
        super(id, version);
        this.animal = animal;
        this.veterinary = veterinary;
        this.dateTime = dateTime;
        this.status = status;
        this.appointmentType = appointmentType;
        this.place = place;
        this.address = address;
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

    public AppointmentStatus getStatus() {
        return status;
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
