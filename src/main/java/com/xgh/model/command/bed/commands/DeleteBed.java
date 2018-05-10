package com.xgh.model.command.bed.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.bed.BedId;

public class DeleteBed implements Command {
	private final BedId id;

	protected DeleteBed() {
		this.id = null;
	}

	@Override
	public BedId getId() {
		return this.id;
	}
}
