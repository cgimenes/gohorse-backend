package com.xgh.model.query.owner;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, UUID> {
	Owner getOneNotDeleted(UUID id);
	
	List<Owner> findAllNotDeleted();

	Page<Owner> findAllNotDeleted(PageRequest request);
}
