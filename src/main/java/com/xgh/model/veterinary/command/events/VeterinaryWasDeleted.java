package com.xgh.model.veterinary.command.events;

import com.xgh.buildingblocks.event.Event;
import com.xgh.model.valueobjects.EntityVersion;
import com.xgh.model.veterinary.command.VeterinaryId;

public class VeterinaryWasDeleted extends Event<VeterinaryId> {
	private static final long serialVersionUID = -3644150398806930108L;
	
	protected VeterinaryWasDeleted() {}
	
	public VeterinaryWasDeleted(VeterinaryId id, EntityVersion version) {
		super(id, version);
	}
}
