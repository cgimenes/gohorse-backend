package com.xgh.model.query.operational.internment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xgh.model.query.operational.animal.Animal;
import com.xgh.model.query.operational.bed.Bed;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "internment")
public class Internment {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "bed_id")
    private Bed bed;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @Column(name = "busy_at")
    private LocalDateTime busyAt;

    @Column(name = "busy_until")
    private LocalDateTime busyUntil;

    private String status;

    @JsonIgnore
    private Boolean deleted = false;

    protected Internment() {
    }

    public Internment(UUID id, Bed bed, Animal animal, LocalDateTime busyAt, LocalDateTime busyUntil, String status, Boolean deleted) {
        this.id = id;
        this.bed = bed;
        this.animal = animal;
        this.busyAt = busyAt;
        this.busyUntil = busyUntil;
        this.status = status;
        this.deleted = deleted;
    }

    public UUID getId() {
        return this.id;
    }

    public Bed getBed() {
        return this.bed;
    }

    public Animal getAnimal() {
        return this.animal;
    }

    public LocalDateTime getBusyAt() {
        return this.busyAt;
    }

    public LocalDateTime getBusyUntil() {
        return this.busyUntil;
    }

    public Boolean isDeleted() {
        return this.deleted;
    }

    public String getStatus() {
        return this.status;
    }

}
