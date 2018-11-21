package com.xgh.model.command.operational.internment.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.model.command.operational.enumerator.EnumeratorId;
import com.xgh.model.command.operational.internment.InternmentStatus;
import com.xgh.model.command.operational.animal.AnimalId;
import com.xgh.model.command.operational.internment.InternmentId;
import java.time.LocalDateTime;

public class InternmentWasRegistered extends EntityEvent<InternmentId> {
    private EnumeratorId bedId;
    private AnimalId animalId;
    private LocalDateTime busyAt;
    private LocalDateTime busyUntil;
    private InternmentStatus status;

    protected InternmentWasRegistered() {}

    public InternmentWasRegistered(InternmentId id, EnumeratorId bedId, AnimalId animalId, LocalDateTime busyAt,
    		LocalDateTime busyUntil, InternmentStatus status, EntityVersion version) {
        super(id, version);
        this.bedId = bedId;
        this.animalId = animalId;
        this.busyAt = busyAt;
        this.busyUntil = busyUntil;
        this.status = status;
    }

    public EnumeratorId getBedId() {
        return this.bedId;
    }

    public AnimalId getAnimalId() {
        return this.animalId;
    }

    public LocalDateTime getBusyAt() {
        return this.busyAt;
    }

    public LocalDateTime getBusyUntil() {
        return this.busyUntil;
    }

    public InternmentStatus getStatus() {
        return this.status;
    }
}
