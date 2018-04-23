package com.xgh.model.command.internment.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.animal.AnimalId;
import com.xgh.model.command.bed.BedId;
import com.xgh.model.command.internment.InternmentId;
import com.xgh.model.command.valueobjects.Date;

public class RegisterInternment implements Command {
    private static final long serialVersionUID = -4390730779141246809L;

    private final InternmentId id;
    private final BedId bedId;
    private final AnimalId animalId;
    private final Date busyAt;
    private final Date busyUntil;

    public RegisterInternment() {
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

    public Date getBusyAt() {
        return busyAt;
    }

    public Date getBusyUntil() {
        return busyUntil;
    }
}
