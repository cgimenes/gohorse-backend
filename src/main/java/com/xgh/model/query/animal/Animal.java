package com.xgh.model.query.animal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xgh.model.query.breed.Breed;
import com.xgh.model.query.owner.Owner;
import com.xgh.model.query.specie.Specie;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "animal")
public final class Animal {
    @Id
    private UUID id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToOne
    @JoinColumn(name = "breed_id")
    private Breed breed;

    @ManyToOne
    @JoinColumn(name = "specie_id")
    private Specie specie;

    private Character sex;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    private Boolean castrated = false;

    private Float weight;

    @JsonIgnore
    private Boolean deleted = false;

    protected Animal() {}

    public Animal(UUID id, String name, Owner owner, Breed breed, Specie specie, Character sex, LocalDate birthDate,
                  Boolean castrated, Float weight, Boolean deleted) {
        super();
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.breed = breed;
        this.specie = specie;
        this.sex = sex;
        this.birthDate = birthDate;
        this.castrated = castrated;
        this.weight = weight;
        this.deleted = deleted;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Owner getOwner() {
        return owner;
    }

    public Breed getBreed() {
        return breed;
    }

    public Specie getSpecie() {
        return specie;
    }

    public Character getSex() {
        return sex;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Boolean isCastrated() {
        return castrated;
    }

    public Float getWeight() {
        return weight;
    }

    public Boolean getDeleted() {
        return deleted;
    }
}
