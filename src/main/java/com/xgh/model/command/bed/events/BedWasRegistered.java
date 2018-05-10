package com.xgh.model.command.bed.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.Event;
import com.xgh.model.command.bed.BedId;
import com.xgh.model.command.valueobjects.Code;

public class BedWasRegistered extends Event<BedId> {
	private Code code;
	private Boolean busy;
	
	protected BedWasRegistered() {}

	public BedWasRegistered(BedId id,Code code, Boolean busy, EntityVersion version) {
		super(id, version);
		this.code = code;
		this.busy = busy;
	}

	public Code getCode() {
		return this.code;
	}

	public Boolean isBusy() {
		return this.busy;
	}
}
