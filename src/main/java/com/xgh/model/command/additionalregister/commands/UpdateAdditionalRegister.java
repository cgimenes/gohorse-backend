package com.xgh.model.command.additionalregister.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.additionalregister.AdditionalRegisterId;
import com.xgh.model.command.valueobjects.Name;
import com.xgh.model.command.valueobjects.Description;

public final class UpdateAdditionalRegister implements Command {
    private final AdditionalRegisterId id;
    private final Name type;
    private final Description value;

    protected UpdateAdditionalRegister() {
        this.id = null;
        this.type = null;
        this.value = null;
    }

    @Override
    public AdditionalRegisterId getId() {
        return id;
    }

    public Name getType() {
        return type;
    }

    public Description getValue() {
        return value;
    }
}
