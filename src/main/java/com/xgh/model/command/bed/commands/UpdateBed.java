package com.xgh.model.command.bed.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.bed.BedId;
import com.xgh.model.command.valueobjects.Code;

public class UpdateBed implements Command {
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
