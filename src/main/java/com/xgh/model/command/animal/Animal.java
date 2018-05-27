package com.xgh.model.command.animal;

import com.xgh.buildingblocks.entity.AggregateRoot;
import com.xgh.exceptions.NullMandatoryArgumentException;
import com.xgh.model.command.animal.events.AnimalWasDeleted;
import com.xgh.model.command.animal.events.AnimalWasRegistered;
import com.xgh.model.command.animal.events.AnimalWasUpdated;
import com.xgh.model.command.owner.OwnerId;
import com.xgh.model.command.valueobjects.Date;
import com.xgh.model.command.valueobjects.Name;
import com.xgh.model.command.valueobjects.Sex;

public final class Animal extends AggregateRoot<AnimalId> {
    private Name name;
    private OwnerId owner;
    private Name breed;
    private Name specie;
    private Sex sex;
    private Date birthDate;
    private Float weight;
    private Boolean castrated = false;

    public void register(AnimalId id, Name name, OwnerId owner, Name breed, Name specie, Sex sex, Date birthDate, Float weight, Boolean castrated) {
        if (id == null) {
            throw new NullMandatoryArgumentException("id");
        }

        if (name == null) {
            throw new NullMandatoryArgumentException("nome");
        }

        if (owner == null) {
            throw new NullMandatoryArgumentException("proprietario");
        }

        if (breed == null) {
            throw new NullMandatoryArgumentException("raça");
        }

        if (specie == null) {
            throw new NullMandatoryArgumentException("espécie");
        }

        if (sex == null) {
            throw new NullMandatoryArgumentException("sexo");
        }

        if (birthDate == null) {
            throw new NullMandatoryArgumentException("data de nascimento");
        }

        recordAndApply(new AnimalWasRegistered(id, name, owner, breed, specie, sex, birthDate, weight, castrated, this.nextVersion()));
    }

    protected void when(AnimalWasRegistered event) {
        this.id = event.getEntityId();
        this.name = event.getName();
        this.owner = event.getOwner();
        this.breed = event.getBreed();
        this.specie = event.getSpecie();
        this.sex = event.getSex();
        this.birthDate = event.getBirthDate();
        this.weight = event.getWeight();
        this.castrated = event.isCastrated();
    }

    protected void when(AnimalWasUpdated event) {
        this.name = event.getName();
        this.owner = event.getOwner();
        this.breed = event.getBreed();
        this.specie = event.getSpecie();
        this.sex = event.getSex();
        this.birthDate = event.getBirthDate();
        this.weight = event.getWeight();
        this.castrated = event.isCastrated();
    }

    protected void when(AnimalWasDeleted event) {
        this.markDeleted();
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

    public Boolean isCastrated() {
        return castrated;
    }

    public void update(Name name, OwnerId owner, Name breed, Name specie, Sex sex, Date birthDate, Float weight, Boolean castrated) {
        recordAndApply(new AnimalWasUpdated(this.id, name, owner, breed, specie, sex, birthDate, weight, castrated, this.nextVersion()));
    }

    public void delete() {
        recordAndApply(new AnimalWasDeleted(this.id, this.nextVersion()));
    }
}
