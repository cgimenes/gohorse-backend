package com.xgh.model.command.operational.bed.commands;

import com.xgh.buildingblocks.command.EntityCommand;
import com.xgh.model.command.operational.bed.BedId;
import com.xgh.model.command.operational.valueobjects.Code;

public class UpdateBed implements EntityCommand {
    private final BedId id;
    private final Code code;

    protected UpdateBed() {
        this.id = null;
        this.code = null;
    }

    @Override
    public BedId getId() {
        return this.id;
    }

    public Code getCode() {
        return this.code;
    }
}
