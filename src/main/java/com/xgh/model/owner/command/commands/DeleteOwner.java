package com.xgh.model.owner.command.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.owner.command.OwnerId;

public class DeleteOwner implements Command {
	private static final long serialVersionUID = -8751170354092451583L;
	private final OwnerId id;
	
	protected DeleteOwner() {
        this.id = null;
    }

    public OwnerId getId() {
        return id;
    }

}
