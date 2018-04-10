package com.xgh.model.veterinary.command;

import java.util.UUID;

import com.xgh.model.valueobjects.command.EntityId;

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
