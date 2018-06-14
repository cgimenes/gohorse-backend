package com.xgh.model.command.internment.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.Event;
import com.xgh.model.command.animal.AnimalId;
import com.xgh.model.command.bed.BedId;
import com.xgh.model.command.internment.InternmentId;
import com.xgh.model.command.valueobjects.Date;

public class InternmentWasUpdated extends Event<InternmentId> {
    private BedId bedId;
    private AnimalId animalId;
    private Date busyAt;
    private Date busyUntil;

    protected InternmentWasUpdated() {}

    public InternmentWasUpdated(InternmentId id, BedId bedId, AnimalId animalId, Date busyAt, Date busyUntil,
                                EntityVersion version) {
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

    public Date getBusyAt() {
        return busyAt;
    }

    public Date getBusyUntil() {
        return busyUntil;
    }

}
