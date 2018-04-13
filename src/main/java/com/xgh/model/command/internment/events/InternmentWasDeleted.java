package com.xgh.model.command.internment.events;

import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.Event;
import com.xgh.model.command.internment.InternmentId;

public class InternmentWasDeleted extends Event<InternmentId> {
	private static final long serialVersionUID = -4619001975602909933L;

	protected InternmentWasDeleted() {

	}

	public InternmentWasDeleted(InternmentId id, EntityVersion version) {
		super(id, version);
	}
}
