package com.xgh.model.command.operational.internment.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.model.command.operational.internment.InternmentStatus;
import com.xgh.model.command.operational.animal.AnimalId;
import com.xgh.model.command.operational.bed.BedId;
import com.xgh.model.command.operational.internment.InternmentId;
import java.time.LocalDateTime;

public class InternmentWasRegistered extends EntityEvent<InternmentId> {
    private BedId bedId;
    private AnimalId animalId;
    private LocalDateTime busyAt;
    private LocalDateTime busyUntil;
    private InternmentStatus status;

    protected InternmentWasRegistered() {}

    public InternmentWasRegistered(InternmentId id, BedId bedId, AnimalId animalId, LocalDateTime busyAt,
    		LocalDateTime busyUntil, InternmentStatus status, EntityVersion version) {
        super(id, version);
        this.bedId = bedId;
        this.animalId = animalId;
        this.busyAt = busyAt;
        this.busyUntil = busyUntil;
        this.status = status;
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
