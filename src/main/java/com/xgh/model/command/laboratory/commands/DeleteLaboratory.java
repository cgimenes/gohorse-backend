package com.xgh.model.command.laboratory.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.laboratory.LaboratoryId;

public class DeleteLaboratory implements Command {
    private final LaboratoryId id;

    protected DeleteLaboratory() {
        this.id = null;
    }

    @Override
    public LaboratoryId getId() {
        return id;
    }
}
