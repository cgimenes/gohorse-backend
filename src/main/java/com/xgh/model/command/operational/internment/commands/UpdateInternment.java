package com.xgh.model.command.operational.internment.commands;

import com.xgh.buildingblocks.command.EntityCommand;
import com.xgh.model.command.operational.animal.AnimalId;
import com.xgh.model.command.operational.enumerator.EnumeratorId;
import com.xgh.model.command.operational.internment.InternmentId;
import java.time.LocalDateTime;

public class UpdateInternment implements EntityCommand {
    private final InternmentId id;
    private final EnumeratorId bedId;
    private final AnimalId animalId;
    private final LocalDateTime busyAt;
    private final LocalDateTime busyUntil;

    public UpdateInternment() {
        this.id = null;
        this.bedId = null;
        this.animalId = null;
        this.busyAt = null;
        this.busyUntil = null;
    }

    @Override
    public InternmentId getId() {
        return id;
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
