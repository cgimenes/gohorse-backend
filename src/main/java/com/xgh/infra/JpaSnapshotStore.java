package com.xgh.infra;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.xgh.buildingblocks.DomainEntity;
import com.xgh.buildingblocks.SnapshotStore;
import com.xgh.exceptions.EntityNotFoundException;
import com.xgh.valueobjects.EntityId;

public abstract class JpaSnapshotStore<EntityType extends DomainEntity<?>, IdType extends EntityId> implements SnapshotStore<EntityType, IdType> {
	@Autowired
	private JpaRepository<EntityType, IdType> repository;

	public boolean existsById(IdType id) {
		return repository.existsById(id);
	}
    
	@Override
	public EntityType pull(IdType id) {
		Optional<EntityType> result = repository.findById(id);
		if (!result.isPresent()) {
			throw new EntityNotFoundException();
		}
		return result.get();
	}
	
	@Override
	public void push(EntityType entity) {
		repository.save(entity);		
	}	

	/*
	 * Método para ser usado nos testes
	 */
	public void deleteAll() {
		repository.deleteAll();
	}
}
