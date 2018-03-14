package com.xgh.buildingblocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.xgh.valueobjects.Id;

public abstract class QueryRepository<IdType extends Id, EntityType extends Entity<IdType>> {
    @Autowired
    protected JdbcTemplate connection;

    public abstract EntityType findById(IdType id);
}
