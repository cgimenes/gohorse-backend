package com.xgh.buildingblocks;

import com.xgh.buildingblocks.DomainEntity;
import com.xgh.valueobjects.EntityId;

public interface SnapshotStore<EntityType extends DomainEntity<?>, IdType extends EntityId> {    
	void push(EntityType entity);

	EntityType pull(IdType id);
}
