package com.xgh.model.query.bed;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BedRepository extends JpaRepository<Bed, UUID>{

	Page<Bed> findByDeletedFalse(Pageable pageable);
	Optional<Bed> findOneByIdAndDeletedFalse(UUID id);
}
