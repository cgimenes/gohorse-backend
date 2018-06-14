package com.xgh.model.command.internment.commands;

import java.time.LocalDateTime;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.animal.AnimalId;
import com.xgh.model.command.bed.BedId;
import com.xgh.model.command.internment.InternmentId;
import com.xgh.model.command.valueobjects.Date;

public class UpdateInternment implements Command {
    private static final long serialVersionUID = -558533399289381447L;

    private final InternmentId id;
    private final BedId bedId;
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
