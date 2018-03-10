package com.xdg.valueobjects;

import com.xdg.buildingblocks.Entity;
import com.xdg.buildingblocks.SingleValueObject;

import java.util.UUID;

//@todo arrumar esse generics
abstract public class Id<T extends Entity> extends SingleValueObject<UUID> {
    public Id(UUID value) {
        super(value);
    }
}
