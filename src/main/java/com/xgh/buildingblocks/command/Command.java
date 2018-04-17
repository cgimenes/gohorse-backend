package com.xgh.buildingblocks.command;

import com.xgh.buildingblocks.entity.EntityId;
import com.xgh.buildingblocks.valueobject.ValueObject;

public interface Command extends ValueObject {
    EntityId getId();
}
