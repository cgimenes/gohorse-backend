package com.xgh.model.command.operational.animal.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.model.command.operational.animal.AnimalId;
import com.xgh.model.command.operational.owner.OwnerId;
import com.xgh.model.command.operational.valueobjects.Name;
import com.xgh.model.command.operational.valueobjects.Sex;
import java.time.LocalDate;

public class AnimalWasUpdated extends EntityEvent<AnimalId> {
    private Name name;
    private OwnerId owner;
    private Name breed;
    private Name specie;
    private Sex sex;
    private LocalDate birthDate;
    private Float weight;
    private boolean castrated;

    protected AnimalWasUpdated() {
    }

    public AnimalWasUpdated(AnimalId id, Name name, OwnerId owner, Name breed, Name specie, Sex sex, LocalDate birthDate,
                            Float weight, boolean castrated, EntityVersion version) {
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

    public Name getBreed() {
        return breed;
    }

    public Name getSpecie() {
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
