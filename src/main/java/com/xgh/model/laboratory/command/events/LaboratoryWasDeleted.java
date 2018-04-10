package com.xgh.model.laboratory.command.events;

import com.xgh.buildingblocks.event.Event;
import com.xgh.model.laboratory.command.LaboratoryId;
import com.xgh.model.valueobjects.EntityVersion;

public class LaboratoryWasDeleted extends Event<LaboratoryId> {
	private static final long serialVersionUID = 6621655908701954439L;

	protected LaboratoryWasDeleted() {}
	
    public LaboratoryWasDeleted(LaboratoryId id, EntityVersion version) {
        super(id, version);
    }
}
