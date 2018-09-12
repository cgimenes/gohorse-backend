package com.xgh.model.command.operational.bed.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.operational.bed.BedId;
import com.xgh.model.command.operational.valueobjects.Code;

public class RegisterBed implements Command {
    private final BedId id;
    private final Code code;

    protected RegisterBed() {
        this.id = new BedId();
        this.code = null;
    }

    @Override
    public BedId getId() {
        return id;
    }

    public Code getCode() {
        return this.code;
    }
}
