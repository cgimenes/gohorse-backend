package com.xgh.model.command.operational.surgery.commands;

import com.xgh.buildingblocks.command.EntityCommand;
import com.xgh.model.command.operational.animal.AnimalId;
import com.xgh.model.command.operational.appointment.AppointmentId;
import com.xgh.model.command.operational.surgery.SurgeryId;
import com.xgh.model.command.operational.surgery.SurgeryStatus;
import com.xgh.model.command.operational.veterinary.VeterinaryId;
import java.time.LocalDateTime;

public class RegisterSurgery implements EntityCommand {
    private SurgeryId id;
    private AnimalId animal;
    private VeterinaryId veterinary;
    private LocalDateTime dateTime;
    private String surgeryType;
    private String notes;
    private SurgeryStatus status;
    private AppointmentId appointment;

    protected RegisterSurgery() {
        this.id = new SurgeryId();
        this.animal = null;
        this.veterinary = null;
        this.dateTime = null;
        this.surgeryType = null;
        this.notes = null;
        this.status = null;
        this.appointment = null;
    }

    @Override
    public SurgeryId getId() {
        return this.id;
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

    public String getSurgeryType() {
        return surgeryType;
    }

    public String getNotes() {
        return notes;
    }

    public SurgeryStatus getStatus() {
        return status;
    }

    public AppointmentId getAppointment() {
        return appointment;
    }

}
