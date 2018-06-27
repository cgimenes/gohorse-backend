package com.xgh.model.command.owner.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.owner.OwnerId;

public class DeleteOwner implements Command {
    
	private static final long serialVersionUID = 6338258403780576284L;
	
	private final OwnerId id;

    protected DeleteOwner() {
        this.id = null;
    }

    @Override
    public OwnerId getId() {
        return id;
    }
}
