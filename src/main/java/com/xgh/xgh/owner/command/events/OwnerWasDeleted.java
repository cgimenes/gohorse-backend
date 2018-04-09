package com.xgh.xgh.owner.command.events;

import com.xgh.buildingblocks.Event;
import com.xgh.valueobjects.EntityVersion;
import com.xgh.xgh.owner.command.OwnerId;

public class OwnerWasDeleted extends Event<OwnerId>{
	private static final long serialVersionUID = 5568983303126584553L;
	
	protected OwnerWasDeleted() {
		
	}
	
	public OwnerWasDeleted(OwnerId id, EntityVersion version) {
		super(id,version);
	}
	
	
}
