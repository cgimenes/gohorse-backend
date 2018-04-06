package com.xgh.xgh.laboratory.command.events;

import com.xgh.buildingblocks.Event;
import com.xgh.valueobjects.EntityVersion;
import com.xgh.xgh.laboratory.command.LaboratoryId;

public class LaboratoryWasDeleted extends Event<LaboratoryId> {
	private static final long serialVersionUID = 6621655908701954439L;

	protected LaboratoryWasDeleted() {}
	
    public LaboratoryWasDeleted(LaboratoryId id, EntityVersion version) {
        super(id, version);
    }
}
