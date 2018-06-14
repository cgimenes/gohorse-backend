package com.xgh.model.command.internment.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.internment.InternmentId;

public class DeleteInternment implements Command {
    private final InternmentId id;

    public DeleteInternment() {
        this.id = null;
    }

    @Override
    public InternmentId getId() {
        return this.id;
    }
}
