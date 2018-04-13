package com.xgh.model.query.laboratory;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaboratoryRepository extends JpaRepository<Laboratory, UUID> {

	Page<Laboratory> findAllNotDeleted(PageRequest request);

	Laboratory getOneNotDeleted(UUID id);
}