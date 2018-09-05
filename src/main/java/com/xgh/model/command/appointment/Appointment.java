package com.xgh.model.command.appointment;

import com.xgh.buildingblocks.entity.AggregateRoot;
import com.xgh.exceptions.NullMandatoryArgumentException;
import com.xgh.model.command.animal.AnimalId;
import com.xgh.model.command.appointment.events.AppointmentWasCancelled;
import com.xgh.model.command.appointment.events.AppointmentWasDeleted;
import com.xgh.model.command.appointment.events.AppointmentWasFinished;
import com.xgh.model.command.appointment.events.AppointmentWasRegistered;
import com.xgh.model.command.appointment.events.AppointmentWasUpdated;
import com.xgh.model.command.valueobjects.Address;
import com.xgh.model.command.veterinary.VeterinaryId;
import java.time.LocalDateTime;

public final class Appointment extends AggregateRoot<AppointmentId> {
    private AnimalId animal;
    private VeterinaryId veterinary;
    private LocalDateTime dateTime;
    private AppointmentStatus status;
    private AppointmentType appointmentType;
    private AppointmentPlace place;
    private Address address;

    public void register(AppointmentId id, AnimalId animal, VeterinaryId veterinary, LocalDateTime dateTime, AppointmentType type, AppointmentPlace place, Address address) {
        if (id == null) {
            throw new NullMandatoryArgumentException("id");
        }

        if (animal == null) {
            throw new NullMandatoryArgumentException("animal");
        }

        if (veterinary == null) {
            throw new NullMandatoryArgumentException("veterinário");
        }

        if (dateTime == null) {
            throw new NullMandatoryArgumentException("horário");
        }

        if (type == null) {
            throw new NullMandatoryArgumentException("tipo");
        }

        if (place == null) {
            throw new NullMandatoryArgumentException("local");
        }

        if (place == AppointmentPlace.OTHER && address == null) {
            throw new NullMandatoryArgumentException("endereço");
        }

        recordAndApply(new AppointmentWasRegistered(id, animal, veterinary, dateTime, AppointmentStatus.SCHEDULED, type, place, address, this.nextVersion()));
    }

    protected void when(AppointmentWasRegistered event) {
        this.animal = event.getAnimal();
        this.veterinary = event.getVeterinary();
        this.dateTime = event.getDateTime();
        this.status = event.getStatus();
        this.appointmentType = event.getAppointmentType();
        this.place = event.getPlace();
        this.address = event.getAddress();
    }

    protected void when(AppointmentWasUpdated event) {
        this.veterinary = event.getVeterinary();
        this.dateTime = event.getDateTime();
        this.place = event.getPlace();
        this.address = event.getAddress();
    }

    protected void when(AppointmentWasCancelled event) {
        this.status = AppointmentStatus.CANCELLED;
    }

    protected void when(AppointmentWasFinished event) {
        this.status = AppointmentStatus.FINISHED;
    }

    protected void when(AppointmentWasDeleted event) {
        this.markDeleted();
    }

    public void update(VeterinaryId veterinary, LocalDateTime dateTime, AppointmentPlace place, Address address) {
        if (veterinary == null) {
            throw new NullMandatoryArgumentException("veterinário");
        }

        if (dateTime == null) {
            throw new NullMandatoryArgumentException("horário");
        }

        if (place == null) {
            throw new NullMandatoryArgumentException("local");
        }

        if (place == AppointmentPlace.OTHER && address == null) {
            throw new NullMandatoryArgumentException("endereço");
        }

        recordAndApply(new AppointmentWasUpdated(this.id, veterinary, dateTime, place, address, this.nextVersion()));
    }

    public void delete() {
        recordAndApply(new AppointmentWasDeleted(this.id, this.nextVersion()));
    }

    public void cancel() {
        recordAndApply(new AppointmentWasCancelled(this.id, this.nextVersion()));
    }

    public void finish() {
        recordAndApply(new AppointmentWasFinished(this.id, this.nextVersion()));
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
