package com.xgh.model.command.animal.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.animal.AnimalId;
import com.xgh.model.command.enumerators.EnumeratorId;
import com.xgh.model.command.owner.OwnerId;
import com.xgh.model.command.valueobjects.Name;
import com.xgh.model.command.valueobjects.Sex;
import java.time.LocalDate;

public final class RegisterAnimal implements Command {
    private final AnimalId id;
    private final Name name;
    private final OwnerId owner;
    private final EnumeratorId breed;
    private final EnumeratorId specie;
    private final Sex sex;
    private final LocalDate birthDate;
    private final Float weight;
    private final boolean castrated;

    protected RegisterAnimal() {
        this.id = new AnimalId();
        this.name = null;
        this.owner = null;
        this.breed = null;
        this.specie = null;
        this.sex = null;
        this.birthDate = null;
        this.weight = null;
        this.castrated = false;
    }

    @Override
    public AnimalId getId() {
        return id;
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
