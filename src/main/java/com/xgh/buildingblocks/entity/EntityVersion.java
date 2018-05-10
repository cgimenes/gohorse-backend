package com.xgh.buildingblocks.entity;

import com.xgh.buildingblocks.valueobject.SingleValueObject;

public class EntityVersion extends SingleValueObject<Integer> {
    protected EntityVersion() {}

    public EntityVersion(Integer version) {
        super(version);
    }

    public EntityVersion next() {
        return new EntityVersion(this.getValue() + 1);
    }

    public boolean isBlank() {
        return this.equals(new EntityVersion(0));
    }
}
