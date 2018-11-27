package com.xgh.model.command.operational.internment.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.model.command.operational.animal.AnimalId;
import com.xgh.model.command.operational.enumerator.EnumeratorId;
import com.xgh.model.command.operational.internment.InternmentId;
import java.time.LocalDateTime;

public class InternmentWasUpdated extends EntityEvent<InternmentId> {
    private EnumeratorId bedId;
    private AnimalId animalId;
    private LocalDateTime busyAt;
    private LocalDateTime busyUntil;

    protected InternmentWasUpdated() {}

    public InternmentWasUpdated(InternmentId id, EnumeratorId bedId, AnimalId animalId,
    		LocalDateTime busyAt, LocalDateTime busyUntil, EntityVersion version) {
        super(id, version);
        this.bedId = bedId;
        this.animalId = animalId;
        this.busyAt = busyAt;
        this.busyUntil = busyUntil;
    }

    public EnumeratorId getBedId() {
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

}
