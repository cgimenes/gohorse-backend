package com.xgh.model.command.internment.events;

import java.time.LocalDateTime;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.Event;
import com.xgh.model.command.animal.AnimalId;
import com.xgh.model.command.bed.BedId;
import com.xgh.model.command.internment.InternmentId;
import com.xgh.model.command.valueobjects.Date;

public class InternmentWasRegistered extends Event<InternmentId> {
    private static final long serialVersionUID = 2553347792033362364L;

    private BedId bedId;
    private AnimalId animalId;
    private LocalDateTime busyAt;
    private LocalDateTime busyUntil;

    protected InternmentWasRegistered() {

    }

    public InternmentWasRegistered(InternmentId id, BedId bedId, AnimalId animalId, 
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
