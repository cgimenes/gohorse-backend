package com.xgh.model.query.internment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xgh.model.query.animal.Animal;
import com.xgh.model.query.bed.Bed;
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

    @JsonIgnore
    private Boolean deleted = false;

    protected Internment() {
    }

    public Internment(UUID id, Bed bed, Animal animal, LocalDateTime busyAt, LocalDateTime busyUntil, Boolean deleted) {
        this.id = id;
        this.bed = bed;
        this.animal = animal;
        this.busyAt = busyAt;
        this.busyUntil = busyUntil;
        this.deleted = deleted;
    }

    public UUID getId() {
        return id;
    }

    public Bed getBed() {
        return bed;
    }

    public Animal getAnimal() {
        return animal;
    }

    public LocalDateTime getBusyAt() {
        return busyAt;
    }

    public LocalDateTime getBusyUntil() {
        return busyUntil;
    }

    public Boolean isDeleted() {
        return deleted;
    }

}
