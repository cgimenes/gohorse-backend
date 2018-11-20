package com.xgh.model.command.operational.animal.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.model.command.operational.animal.AnimalId;
import com.xgh.model.command.operational.enumerator.EnumeratorId;
import com.xgh.model.command.operational.owner.OwnerId;
import com.xgh.model.command.operational.valueobjects.Name;
import java.time.LocalDate;

public class AnimalWasRegistered extends EntityEvent<AnimalId> {
    private Name name;
    private OwnerId owner;
    private EnumeratorId breed;
    private EnumeratorId specie;
    private EnumeratorId sex;
    private LocalDate birthDate;
    private Float weight;
    private boolean castrated;

    protected AnimalWasRegistered() {
    }

    public AnimalWasRegistered(AnimalId id, Name name, OwnerId owner, EnumeratorId breed, EnumeratorId specie, EnumeratorId sex, LocalDate birthDate, Float weight, boolean castrated, EntityVersion version) {
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

    public EnumeratorId getSex() {
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
