package com.xgh.model.query.operational.appointment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xgh.model.query.operational.address.Address;
import com.xgh.model.query.operational.animal.Animal;
import com.xgh.model.query.operational.veterinary.Veterinary;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "appointment")
public final class Appointment {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "veterinary_id")
    private Veterinary veterinary;

    private LocalDateTime dateTime;

    private String status;

    @Column(name = "type")
    private String appointmentType;

    private String place;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @JsonIgnore
    private Boolean deleted = false;

    protected Appointment() {
    }

    public Appointment(UUID id, Animal animal, Veterinary veterinary, LocalDateTime dateTime, String status, String appointmentType, String place, Address address, Boolean deleted) {
        super();
        this.id = id;
        this.animal = animal;
        this.veterinary = veterinary;
        this.dateTime = dateTime;
        this.status = status;
        this.appointmentType = appointmentType;
        this.place = place;
        this.address = address;
        this.deleted = deleted;
    }

    public UUID getId() {
        return id;
    }

    public Animal getAnimal() {
        return animal;
    }

    public Veterinary getVeterinary() {
        return veterinary;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getStatus() {
        return status;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public String getPlace() {
        return place;
    }

    public Address getAddress() {
        return address;
    }

    public Boolean getDeleted() {
        return deleted;
    }
}
