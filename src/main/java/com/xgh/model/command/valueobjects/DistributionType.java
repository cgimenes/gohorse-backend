package com.xgh.model.command.valueobjects;

import com.xgh.buildingblocks.valueobject.ValueObject;

public class DistributionType implements ValueObject {

	private static final long serialVersionUID = 6090488518103456243L;

    private String name;

    protected DistributionType() {}

    public DistributionType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
