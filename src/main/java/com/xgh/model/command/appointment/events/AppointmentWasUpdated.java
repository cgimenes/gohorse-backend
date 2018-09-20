package com.xgh.model.command.appointment.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.Event;
import com.xgh.model.command.appointment.AppointmentId;
import com.xgh.model.command.appointment.AppointmentPlace;
import com.xgh.model.command.valueobjects.Address;
import com.xgh.model.command.veterinary.VeterinaryId;
import java.time.LocalDateTime;

public class AppointmentWasUpdated extends Event<AppointmentId> {
    private VeterinaryId veterinary;
    private LocalDateTime dateTime;
    private AppointmentPlace place;
    private Address address;

    protected AppointmentWasUpdated() {
    }

    public AppointmentWasUpdated(AppointmentId id, VeterinaryId veterinary, LocalDateTime dateTime, AppointmentPlace place, Address address, EntityVersion version) {
        super(id, version);
        this.veterinary = veterinary;
        this.dateTime = dateTime;
        this.place = place;
        this.address = address;
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
