package com.xgh.model.query.laboratory;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaboratoryRepository extends JpaRepository<Laboratory, UUID> {

	Page<Laboratory> findByDeletedFalse(Pageable pageable);

	Optional<Laboratory> findOneByIdAndDeletedFalse(UUID id);
}