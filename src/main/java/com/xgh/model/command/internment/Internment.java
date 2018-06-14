package com.xgh.model.command.internment;

import com.xgh.buildingblocks.entity.AggregateRoot;
import com.xgh.exceptions.NullMandatoryArgumentException;
import com.xgh.model.command.animal.AnimalId;
import com.xgh.model.command.bed.BedId;
import com.xgh.model.command.internment.events.InternmentWasDeleted;
import com.xgh.model.command.internment.events.InternmentWasRegistered;
import com.xgh.model.command.internment.events.InternmentWasUpdated;
import com.xgh.model.command.valueobjects.Date;

public class Internment extends AggregateRoot<InternmentId> {
    private BedId bedId;
    private AnimalId animalId;
    private Date busyAt;
    private Date busyUntil;

    public Internment() {
        super();
    }

    public void register(InternmentId id, BedId bedId, AnimalId animalId, Date busyAt, Date busyUntil) {
        if (id == null) {
            throw new NullMandatoryArgumentException("id");
        }

        if (bedId == null) {
            throw new NullMandatoryArgumentException("leito");
        }

        if (animalId == null) {
            throw new NullMandatoryArgumentException("animal");
        }

        if (busyAt == null) {
            throw new NullMandatoryArgumentException("data de entrada");
        }

        recordAndApply(new InternmentWasRegistered(id, bedId, animalId, busyAt, busyUntil, this.nextVersion()));
    }

    public void update(BedId bedId, AnimalId animalId, Date busyAt, Date busyUntil) {
        recordAndApply(new InternmentWasUpdated(this.id, bedId, animalId, busyAt, busyUntil, this.nextVersion()));
    }

    public void delete() {
        recordAndApply(new InternmentWasDeleted(this.id, this.nextVersion()));
    }

    protected void when(InternmentWasRegistered event) {
        this.bedId = event.getBedId();
        this.animalId = event.getAnimalId();
        this.busyAt = event.getBusyAt();
        this.busyUntil = event.getBusyUntil();
    }

    protected void when(InternmentWasUpdated event) {
        this.bedId = event.getBedId();
        this.animalId = event.getAnimalId();
        this.busyAt = event.getBusyAt();
        this.busyUntil = event.getBusyUntil();
    }

    protected void when(InternmentWasDeleted event) {
        this.markDeleted();
    }

    public BedId getBedId() {
        return bedId;
    }

    public AnimalId getAnimalId() {
        return animalId;
    }

    public Date getBusyAt() {
        return busyAt;
    }

    public Date getBusyUntil() {
        return busyUntil;
    }

}
