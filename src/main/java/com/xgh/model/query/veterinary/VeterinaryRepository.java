package com.xgh.model.query.veterinary;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeterinaryRepository extends JpaRepository<Veterinary, UUID> {

	Page<Veterinary> findAllNotDeleted(PageRequest request);

	Veterinary getOneNotDeleted(UUID id);

}
