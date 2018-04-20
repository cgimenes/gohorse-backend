package com.xgh.model.command.bed.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.bed.BedId;

public class DeleteBed implements Command{

	private static final long serialVersionUID = 9197323014302677557L;
	
	private final BedId id;

	protected DeleteBed() {
		this.id = null;
	}

	public BedId getId() {
		return this.id;
	}
	
}
