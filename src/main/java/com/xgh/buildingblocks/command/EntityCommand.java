package com.xgh.buildingblocks.command;

import com.xgh.buildingblocks.entity.EntityId;

public interface EntityCommand extends Command {
    EntityId getId();
}
