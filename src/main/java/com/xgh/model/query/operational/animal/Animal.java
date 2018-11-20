package com.xgh.model.query.operational.animal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xgh.model.query.operational.enumerator.Enumerator;
import com.xgh.model.query.operational.owner.Owner;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
    private Enumerator breed;

    @ManyToOne
    @JoinColumn(name = "specie_id")
    private Enumerator specie;

    @ManyToOne
    @JoinColumn(name = "sex_id")
    private Enumerator sex;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    private Boolean castrated = false;

    private Float weight;

    @JsonIgnore
    private Boolean deleted = false;

    protected Animal() {
    }

    public Animal(UUID id, String name, Owner owner, Enumerator breed, Enumerator specie, Enumerator sex, LocalDate birthDate,
                  Boolean castrated, Float weight, Boolean deleted) {
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

    public Enumerator getBreed() {
        return breed;
    }

    public Enumerator getSpecie() {
        return specie;
    }

    public Enumerator getSex() {
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
