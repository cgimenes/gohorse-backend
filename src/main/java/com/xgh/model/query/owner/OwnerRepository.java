package com.xgh.model.query.owner;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, UUID> {
	Optional<Owner> findOneByIdAndDeletedFalse(UUID id);

	Page<Owner> findByDeletedFalse(Pageable pageable);
}
