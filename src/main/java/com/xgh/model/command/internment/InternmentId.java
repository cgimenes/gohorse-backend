package com.xgh.model.command.internment;

import java.util.UUID;

import com.xgh.buildingblocks.entity.EntityId;

public class InternmentId extends EntityId{
	private static final long serialVersionUID = 6881805650125383364L;
	
	public InternmentId(UUID value) {
		super(value);
	}
	
	public InternmentId(String value) {
		super(value);
	}
	
	public InternmentId() {
		super();
	}
}
