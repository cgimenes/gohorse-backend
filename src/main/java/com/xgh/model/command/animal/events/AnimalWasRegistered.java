package com.xgh.model.command.animal.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.Event;
import com.xgh.model.command.animal.AnimalId;
import com.xgh.model.command.enumerators.EnumeratorId;
import com.xgh.model.command.owner.OwnerId;
import com.xgh.model.command.valueobjects.Name;
import com.xgh.model.command.valueobjects.Sex;
import java.time.LocalDate;

public class AnimalWasRegistered extends Event<AnimalId> {
    private Name name;
    private OwnerId owner;
    private EnumeratorId breed;
    private EnumeratorId specie;
    private Sex sex;
    private LocalDate birthDate;
    private Float weight;
    private boolean castrated;

    protected AnimalWasRegistered() {
    }

    public AnimalWasRegistered(AnimalId id, Name name, OwnerId owner, EnumeratorId breed, EnumeratorId specie, Sex sex, LocalDate birthDate, Float weight, boolean castrated, EntityVersion version) {
        super(id, version);
        this.name = name;
        this.owner = owner;
        this.breed = breed;
        this.specie = specie;
        this.sex = sex;
        this.birthDate = birthDate;
        this.weight = weight;
        this.castrated = castrated;
    }

    public Name getName() {
        return name;
    }

    public OwnerId getOwner() {
        return owner;
    }

    public EnumeratorId getBreed() {
        return breed;
    }

    public EnumeratorId getSpecie() {
        return specie;
    }

    public Sex getSex() {
        return sex;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Float getWeight() {
        return weight;
    }

    public boolean isCastrated() {
        return castrated;
    }
}
