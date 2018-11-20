package com.xgh.model.command.operational.animal;

import com.xgh.buildingblocks.entity.AggregateRoot;
import com.xgh.exceptions.NullMandatoryArgumentException;
import com.xgh.model.command.operational.animal.events.AnimalWasDeleted;
import com.xgh.model.command.operational.animal.events.AnimalWasRegistered;
import com.xgh.model.command.operational.animal.events.AnimalWasUpdated;
import com.xgh.model.command.operational.enumerator.EnumeratorId;
import com.xgh.model.command.operational.owner.OwnerId;
import com.xgh.model.command.operational.valueobjects.Name;
import java.time.LocalDate;

public final class Animal extends AggregateRoot<AnimalId> {
    private Name name;
    private OwnerId owner;
    private EnumeratorId breed;
    private EnumeratorId specie;
    private EnumeratorId sex;
    private LocalDate birthDate;
    private Float weight;
    private Boolean castrated = false;

    public void register(AnimalId id, Name name, OwnerId owner, EnumeratorId breed, EnumeratorId specie, EnumeratorId sex, LocalDate birthDate, Float weight, Boolean castrated) {
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

    public Boolean isCastrated() {
        return castrated;
    }

    public void update(Name name, OwnerId owner, EnumeratorId breed, EnumeratorId specie, EnumeratorId sex, LocalDate birthDate, Float weight, Boolean castrated) {
        recordAndApply(new AnimalWasUpdated(this.id, name, owner, breed, specie, sex, birthDate, weight, castrated, this.nextVersion()));
    }

    public void delete() {
        recordAndApply(new AnimalWasDeleted(this.id, this.nextVersion()));
    }
}
