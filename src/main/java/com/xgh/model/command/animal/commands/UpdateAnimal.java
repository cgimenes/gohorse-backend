package com.xgh.model.command.animal.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.animal.AnimalId;
import com.xgh.model.command.owner.OwnerId;
import com.xgh.model.command.valueobjects.Name;
import com.xgh.model.command.valueobjects.Sex;
import java.time.LocalDate;

public class UpdateAnimal implements Command {
    private final AnimalId id;
    private final Name name;
    private final OwnerId owner;
    private final Name breed;
    private final Name specie;
    private final Sex sex;
    private final LocalDate birthDate;
    private final Float weight;
    private final boolean castrated;

    protected UpdateAnimal() {
        this.id = null;
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
