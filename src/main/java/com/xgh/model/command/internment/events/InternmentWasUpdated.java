package com.xgh.model.command.internment.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.Event;
import com.xgh.model.command.animal.AnimalId;
import com.xgh.model.command.bed.BedId;
import com.xgh.model.command.internment.InternmentId;
import java.time.LocalDateTime;

public class InternmentWasUpdated extends Event<InternmentId> {
    private BedId bedId;
    private AnimalId animalId;
    private LocalDateTime busyAt;
    private LocalDateTime busyUntil;

    protected InternmentWasUpdated() {}

    public InternmentWasUpdated(InternmentId id, BedId bedId, AnimalId animalId,
    		LocalDateTime busyAt, LocalDateTime busyUntil, EntityVersion version) {
        super(id, version);
        this.bedId = bedId;
        this.animalId = animalId;
        this.busyAt = busyAt;
        this.busyUntil = busyUntil;
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

}
