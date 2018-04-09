package com.xgh.xgh.owner.command;

import java.util.UUID;

import com.xgh.valueobjects.EntityId;

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
