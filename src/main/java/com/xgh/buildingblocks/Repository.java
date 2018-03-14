package com.xgh.buildingblocks;

import com.xgh.valueobjects.Id;

public abstract class Repository<IdType extends Id, EntityType extends Entity<IdType>> 
	extends QueryRepository<IdType, EntityType> 
{
    public abstract void insert(EntityType entityType);

    public abstract void update(EntityType entityType);
}
