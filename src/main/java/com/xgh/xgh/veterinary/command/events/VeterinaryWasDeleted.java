package com.xgh.xgh.veterinary.command.events;

import com.xgh.buildingblocks.Event;
import com.xgh.valueobjects.EntityVersion;
import com.xgh.xgh.veterinary.command.VeterinaryId;

public class VeterinaryWasDeleted extends Event<VeterinaryId> {
	private static final long serialVersionUID = -3644150398806930108L;
	
	protected VeterinaryWasDeleted() {}
	
	public VeterinaryWasDeleted(VeterinaryId id, EntityVersion version) {
		super(id, version);
	}
}
