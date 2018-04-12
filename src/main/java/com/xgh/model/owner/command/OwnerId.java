package com.xgh.model.owner.command;

import java.util.UUID;

import com.xgh.model.valueobjects.command.EntityId;

public class OwnerId extends EntityId{
	private static final long serialVersionUID = -3894150224399325753L;
	
	public OwnerId() {
		super();
	}
	
	public OwnerId(String value) {
		super(value);
	}
	
	public OwnerId(UUID value) {
		super(value);
	}

}
