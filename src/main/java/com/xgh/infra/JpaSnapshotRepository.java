package com.xgh.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xgh.buildingblocks.DomainEntity;
import com.xgh.valueobjects.EntityId;

@Repository
public interface JpaSnapshotRepository<EntityType extends DomainEntity<?>, IdType extends EntityId> 
		extends JpaRepository<EntityType, IdType> {
}
