package com.xgh.model.command.bed.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.Event;
import com.xgh.model.command.bed.BedId;
import com.xgh.model.command.valueobjects.Code;

public class BedWasRegistered extends Event<BedId> {


	private static final long serialVersionUID = -8902279691670887369L;

	private Code code;
	
	protected BedWasRegistered() {}

	public BedWasRegistered(BedId id,Code code, EntityVersion version) {
		super(id, version);
		this.code = code;
	}

	public Code getCode() {
		return this.code;
	}

	
	
	
	
	
	
}
