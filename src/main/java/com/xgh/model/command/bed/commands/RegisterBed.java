package com.xgh.model.command.bed.commands;

import com.xgh.buildingblocks.command.Command;
import com.xgh.model.command.bed.BedId;
import com.xgh.model.command.valueobjects.Code;

public class RegisterBed implements Command{

	private static final long serialVersionUID = 1825556867663334196L;
	
	private final BedId id;
	private final Code code;
	
	protected RegisterBed() {
		this.id = new BedId();
		this.code = null;
	}

	@Override
	public BedId getId() {
		return id;
	}

	public Code getCode() {
		return this.code;
	}

	
	
	
}
