package com.xgh.model.command.veterinary;

import java.util.UUID;

import com.xgh.buildingblocks.entity.EntityId;

public class VeterinaryId extends EntityId {
	private static final long serialVersionUID = 7093817763360378727L;

	public VeterinaryId() {
		super();
	}
	
	public VeterinaryId(String value) {
		super(value);
	}

	public VeterinaryId(UUID value) {
		super(value);
	}
}
