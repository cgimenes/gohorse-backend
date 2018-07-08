package com.xgh.model.command.additionalregister.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.additionalregister.AdditionalRegisterId;

public class DeleteAdditionalRegister implements Command {
    private final AdditionalRegisterId id;

    protected DeleteAdditionalRegister() {
        this.id = null;
    }

    @Override
    public AdditionalRegisterId getId() {
        return id;
    }
}
