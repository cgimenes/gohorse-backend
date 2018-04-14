package com.xgh.model.command.laboratory.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.laboratory.LaboratoryId;

public class DeleteLaboratory implements Command {
    private static final long serialVersionUID = 3670237845509237094L;

    private final LaboratoryId id;

    protected DeleteLaboratory() {
        this.id = null;
    }

    public LaboratoryId getId() {
        return id;
    }
}
