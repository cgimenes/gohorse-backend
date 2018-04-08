package com.xgh.xgh.laboratory.command.commands;

import com.xgh.buildingblocks.Command;
import com.xgh.xgh.laboratory.command.LaboratoryId;

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
