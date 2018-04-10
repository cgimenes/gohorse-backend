package com.xgh.model.laboratory.command.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.laboratory.command.LaboratoryId;

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
