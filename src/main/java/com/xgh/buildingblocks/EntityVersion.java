package com.xgh.buildingblocks;

public class EntityVersion extends SingleValueObject<Integer> {

	public EntityVersion(Integer version) {
		super(version);
	}
	
	public EntityVersion next() {
		return new EntityVersion(this.getValue() + 1);
	}
}
