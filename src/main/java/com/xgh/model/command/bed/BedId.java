package com.xgh.model.command.bed;

import java.util.UUID;

import com.xgh.buildingblocks.entity.EntityId;

public class BedId extends EntityId{


	private static final long serialVersionUID = -332889676733604034L;
	
	public BedId(String value) {
		super(value);
	}
	
	public BedId(UUID value) {
		super(value);
	}
	
	public BedId() {
		super();
	}

}