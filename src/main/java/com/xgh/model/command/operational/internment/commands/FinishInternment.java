package com.xgh.model.command.operational.internment.commands;

import com.xgh.buildingblocks.command.EntityCommand;
import com.xgh.model.command.operational.internment.InternmentId;

public class FinishInternment implements EntityCommand {
    private final InternmentId id;

    public FinishInternment() {
        this.id = null;
    }

    @Override
    public InternmentId getId() {
        return this.id;
    }
}
