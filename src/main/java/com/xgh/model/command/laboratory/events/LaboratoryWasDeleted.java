package com.xgh.model.command.laboratory.events;

import com.xgh.buildingblocks.event.Event;
import com.xgh.model.command.laboratory.LaboratoryId;
import com.xgh.model.command.valueobjects.EntityVersion;

public class LaboratoryWasDeleted extends Event<LaboratoryId> {
	private static final long serialVersionUID = 6621655908701954439L;

	protected LaboratoryWasDeleted() {}
	
    public LaboratoryWasDeleted(LaboratoryId id, EntityVersion version) {
        super(id, version);
    }
}
