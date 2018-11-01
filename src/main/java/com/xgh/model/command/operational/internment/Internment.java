package com.xgh.model.command.operational.internment;

import com.xgh.buildingblocks.entity.AggregateRoot;
import com.xgh.exceptions.InvalidArgumentException;
import com.xgh.exceptions.NullMandatoryArgumentException;
import com.xgh.model.command.operational.internment.events.InternmentWasFinished;
import com.xgh.model.command.operational.animal.AnimalId;
import com.xgh.model.command.operational.bed.BedId;
import com.xgh.model.command.operational.internment.events.InternmentWasDeleted;
import com.xgh.model.command.operational.internment.events.InternmentWasRegistered;
import com.xgh.model.command.operational.internment.events.InternmentWasUpdated;
import java.time.LocalDateTime;

public class Internment extends AggregateRoot<InternmentId> {
    private BedId bedId;
    private AnimalId animalId;
    private LocalDateTime busyAt;
    private LocalDateTime busyUntil;
    private InternmentStatus status;

    public Internment() {
        super();
    }

    public void register(InternmentId id, BedId bedId, AnimalId animalId, LocalDateTime busyAt, LocalDateTime busyUntil) {
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

        if (busyUntil != null && busyUntil.isBefore(busyAt)) {
            throw new InvalidArgumentException("data de saída", "A data de saída não pode ser anterior à de entrada");
        }

        recordAndApply(new InternmentWasRegistered(id, bedId, animalId, busyAt, busyUntil, InternmentStatus.ACTIVE, this.nextVersion()));
    }

    public void update(BedId bedId, AnimalId animalId, LocalDateTime busyAt, LocalDateTime busyUntil) {
        recordAndApply(new InternmentWasUpdated(this.id, bedId, animalId, busyAt, busyUntil, this.nextVersion()));
    }

    public void delete() {
        recordAndApply(new InternmentWasDeleted(this.id, this.nextVersion()));
    }

    public void finish() {
        recordAndApply(new InternmentWasFinished(this.id, this.nextVersion()));
    }

    protected void when(InternmentWasRegistered event) {
        this.bedId = event.getBedId();
        this.animalId = event.getAnimalId();
        this.busyAt = event.getBusyAt();
        this.busyUntil = event.getBusyUntil();
        this.status = event.getStatus();
    }

    protected void when(InternmentWasUpdated event) {
        this.bedId = event.getBedId();
        this.animalId = event.getAnimalId();
        this.busyAt = event.getBusyAt();
        this.busyUntil = event.getBusyUntil();
    }

    protected void when(InternmentWasFinished event) {
        this.status = InternmentStatus.FINISHED;
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

    public LocalDateTime getBusyAt() {
        return busyAt;
    }

    public LocalDateTime getBusyUntil() {
        return busyUntil;
    }

    public InternmentStatus getStatus() {
        return status;
    }
}
