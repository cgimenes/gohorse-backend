package com.xgh.model.command.operational.internment.commands;

import com.xgh.buildingblocks.command.EntityCommand;
import com.xgh.model.command.operational.internment.InternmentStatus;
import com.xgh.model.command.operational.animal.AnimalId;
import com.xgh.model.command.operational.bed.BedId;
import com.xgh.model.command.operational.internment.InternmentId;
import java.time.LocalDateTime;

public class RegisterInternment implements EntityCommand {
    private final InternmentId id;
    private final BedId bedId;
    private final AnimalId animalId;
    private final LocalDateTime busyAt;
    private final LocalDateTime busyUntil;
    private final InternmentStatus status;

    public RegisterInternment() {
        this.id = new InternmentId();
        this.bedId = null;
        this.animalId = null;
        this.busyAt = null;
        this.busyUntil = null;
        this.status = null;
    }

    @Override
    public InternmentId getId() {
        return this.id;
    }

    public BedId getBedId() {
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
