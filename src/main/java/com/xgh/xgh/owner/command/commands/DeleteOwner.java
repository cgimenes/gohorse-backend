package com.xgh.xgh.owner.command.commands;

import com.xgh.buildingblocks.Command;
import com.xgh.xgh.owner.command.OwnerId;

public final class DeleteOwner extends Command {
	private static final long serialVersionUID = -8751170354092451583L;
	private final OwnerId id;
	
	protected DeleteOwner() {
        this.id = null;
    }

    public OwnerId getId() {
        return id;
    }

}
