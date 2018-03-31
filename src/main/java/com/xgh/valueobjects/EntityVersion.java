package com.xgh.valueobjects;

import com.xgh.buildingblocks.SingleValueObject;

public class EntityVersion extends SingleValueObject<Integer> {
	private static final long serialVersionUID = -4761548546820727969L;

	protected EntityVersion() {		
	}
	
	public EntityVersion(Integer version) {
		super(version);
	}
	
	public EntityVersion next() {
		return new EntityVersion(this.getValue() + 1);
	}
}
