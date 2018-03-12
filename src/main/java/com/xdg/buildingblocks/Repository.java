package com.xdg.buildingblocks;

import com.xdg.valueobjects.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class Repository<IdType extends Id, EntityType extends Entity> {
    @Autowired
    protected JdbcTemplate connection;

    public abstract EntityType findById(IdType id);

    public abstract void insert(EntityType entityType);

    public abstract void update(EntityType entityType);
}
