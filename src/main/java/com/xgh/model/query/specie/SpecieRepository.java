package com.xgh.model.query.specie;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecieRepository extends JpaRepository<Specie, UUID> {
	Page<Specie> findByNameContainingIgnoreCase(Pageable pageable, String name);
}
