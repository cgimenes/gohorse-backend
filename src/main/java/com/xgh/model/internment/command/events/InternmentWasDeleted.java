package com.xgh.model.internment.command.events;

import com.xgh.buildingblocks.event.Event;
import com.xgh.model.internment.command.InternmentId;
import com.xgh.model.valueobjects.command.EntityVersion;

public class InternmentWasDeleted extends Event<InternmentId> {
	private static final long serialVersionUID = -4619001975602909933L;

	protected InternmentWasDeleted() {

	}

	public InternmentWasDeleted(InternmentId id, EntityVersion version) {
		super(id, version);
	}
}
