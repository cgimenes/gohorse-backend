package com.xgh.model.command.animal.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.animal.AnimalId;
import com.xgh.model.command.owner.OwnerId;
import com.xgh.model.command.valueobjects.Date;
import com.xgh.model.command.valueobjects.Name;
import com.xgh.model.command.valueobjects.Sex;

public final class RegisterAnimal implements Command {
    private AnimalId id;
    private Name name;
    private OwnerId owner;
    private Name breed;
    private Name specie;
    private Sex sex;
    private Date birthDate;
    private Float weight;
    private boolean castrated;

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

    public Date getBirthDate() {
        return birthDate;
    }

    public Float getWeight() {
        return weight;
    }

    public boolean isCastrated() {
        return castrated;
    }
}
