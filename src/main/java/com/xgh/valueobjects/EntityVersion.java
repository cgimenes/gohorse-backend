package com.xgh.valueobjects;

import com.xgh.buildingblocks.SingleValueObject;

public class EntityVersion extends SingleValueObject<Integer> {

	public EntityVersion(Integer version) {
		super(version);
	}
	
	public EntityVersion next() {
		return new EntityVersion(this.getValue() + 1);
	}
}
