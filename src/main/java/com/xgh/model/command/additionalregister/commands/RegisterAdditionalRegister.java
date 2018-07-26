package com.xgh.model.command.additionalregister.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.additionalregister.AdditionalRegisterId;
import com.xgh.model.command.valueobjects.Description;
import com.xgh.model.command.valueobjects.Name;

public final class RegisterAdditionalRegister implements Command {
    private final AdditionalRegisterId id;
    private final Name type;
    private final Description value;

    protected RegisterAdditionalRegister() {
        this.id = new AdditionalRegisterId();
        this.type = null;
        this.value = null;
    }

    @Override
    public AdditionalRegisterId getId() {
        return this.id;
    }

    public Name getType() {
        return this.type;
    }

    public Description getValue() {
        return this.value;
    }
}
