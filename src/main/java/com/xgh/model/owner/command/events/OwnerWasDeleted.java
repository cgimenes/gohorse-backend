package com.xgh.model.owner.command.events;

import com.xgh.buildingblocks.event.Event;
import com.xgh.model.owner.command.OwnerId;
import com.xgh.model.valueobjects.command.EntityVersion;

public class OwnerWasDeleted extends Event<OwnerId>{
	private static final long serialVersionUID = 5568983303126584553L;
	
	protected OwnerWasDeleted() {
		
	}
	
	public OwnerWasDeleted(OwnerId id, EntityVersion version) {
		super(id,version);
	}
	
	
}
